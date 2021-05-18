package com.binpe.gooddata.coffeemachines.api.services

import com.binpe.gooddata.coffeemachines.api.IMachineService
import com.binpe.gooddata.coffeemachines.api.ITransactionService
import com.binpe.gooddata.coffeemachines.api.IUserService
import com.binpe.gooddata.coffeemachines.api.errors.ServiceException
import com.binpe.gooddata.coffeemachines.api.utils.ApiUtils.toDT
import com.binpe.gooddata.coffeemachines.data.cache.TransactionCache
import com.binpe.gooddata.coffeemachines.data.models.TransactionModel
import com.binpe.gooddata.coffeemachines.data.repositories.TransactionRepository
import org.joda.time.DateTime
import org.joda.time.Duration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.roundToInt

@Service
class TransactionService @Autowired constructor(
    val transactionRepository: TransactionRepository,
    val userService: IUserService,
    val machineService: IMachineService,
    val transactionCache: TransactionCache
) : ITransactionService {
    override fun registerTransaction(transactionModel: TransactionModel) {
        if (!userService.userExists(transactionModel.userID)) throw ServiceException("User does not exist")
        if (!machineService.machineExists(transactionModel.machineID)) throw ServiceException("Machine does not exist")

        saveToCache(transactionModel)

        transactionRepository.save(transactionModel)
    }

    override fun getTransactions(): List<TransactionModel> = transactionRepository
        .findAll()
        .toList()

    override fun getTransactionsUser(userID: Long): List<TransactionModel> = transactionRepository
        .findAllByUserID(userID)
        .toList()

    override fun getTransactionsMachine(machineID: Long): List<TransactionModel> = transactionRepository
        .findAllByMachineID(machineID)
        .toList()

    override fun computeUserCaffeineLevel(userID: Long): List<Double> {
        val actualTime = DateTime.now()
        val userTransactions = transactionCache.getCachedSortedTransactions(userID)
        val computedEntries = userTransactions.map { computeOneEntry(it.caffeine, it.timestamp, actualTime) }

        val foldedResult = computedEntries.fold(
            (List(24) { 0.0 }),
            { finalList, entry ->
                zipLists(finalList, entry)
            }
        )

        return foldedResult
    }

    private fun computeOneEntry(caffeine: Int, timestamp: DateTime, actualTime: DateTime): List<Double> {
        // Reference list for every hour
        val computedHourLevels = MutableList(24) { 0.0 }

        // List of final values of caffeine, first our is in index 0
        val computedLevels = mutableListOf<Double>()

        // Count hours between purchase timestamp and actual time
        // Saved purchase timestamp shouldn't be after actual date (checked when saving)
        val hourCount = Duration(timestamp, actualTime).standardHours
            .takeIf { it > 0 } // Check if at least one hour passed
            ?.let { it - 1 }
            ?: return computedHourLevels

        // Get the count of how much the caffeine should be divided
        val partitionCount = hourCount / 5

        var actualCaffeine = caffeine.toDouble()
        var i = 0
        do {
            computedLevels.add(i, actualCaffeine)
            actualCaffeine = (actualCaffeine / 2).round3digits() // For simplicity some dummy rounding
            i++
        } while (i < partitionCount)

        val sublist = computedLevels.takeLast(24).reversed()
        val extended = sublist + Array(computedHourLevels.size - sublist.size) { 0.0 }
        // Transform to list and take only max 24 items = past day
        return zipLists(computedHourLevels, extended)
    }

    private fun saveToCache(transactionModel: TransactionModel) {
        val machineModel = machineService.getMachineByIDOrThrow(transactionModel.machineID)
        transactionCache.putTransaction(
            userID = transactionModel.userID,
            caffeine = machineModel.caffeine,
            timestamp = transactionModel.timestamp.toDT()
        )
    }

    private fun Double.round3digits(): Double = (this * 1000.0).roundToInt() / 1000.0

    private fun zipLists(first: List<Double>, second: List<Double>) = first.zip(second) { xv, yv -> xv + yv }
}

/*
fun main() {
    val list1 = listOf(1,2,3)
    val list2 = listOf(1,2,3)
    val both = listOf(list1, list2)

    val foldedResult = both.fold(
        (List(24) { 0.0 }),
        { finalList, entry ->
            finalList.zip(entry) { xv, yv -> xv + yv }
        }
    )

    println(foldedResult)
}*/
