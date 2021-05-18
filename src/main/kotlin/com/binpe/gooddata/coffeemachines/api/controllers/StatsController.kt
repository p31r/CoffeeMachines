package com.binpe.gooddata.coffeemachines.api.controllers

import com.binpe.gooddata.coffeemachines.api.ITransactionService
import com.binpe.gooddata.coffeemachines.api.controllers.StatsController.TransactionDTO.Companion.mapFunction
import com.binpe.gooddata.coffeemachines.data.models.TransactionModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * Basic AP for Statistic - for now supports only:
 *  - retrieving of all stats
 *  - retrieving of all stats for a user
 *  - retrieving of all stats for a machine
 *  - retrieving caffeine levels for a user
 *
 *
 * Custom Exceptions are resolved by [com.binpe.gooddata.coffeemachines.api.errors.RestResponseEntityExceptionHandler]
 *
 * @author  Petr Binčík
 * @version 1.0
 * */
@RestController
@RequestMapping(path = ["/stats"], produces = [MimeTypeUtils.APPLICATION_JSON_VALUE])
class StatsController @Autowired constructor(
    val transactionService: ITransactionService
) {
    /**
     * Helping DTO for transferring Transactions
     *
     * @param   userID      ID of the user
     * @param   machineID   ID of the machine
     * @param   timestamp   ISO-8601 string format of purchase time
     * */
    data class TransactionDTO(
        val userID: Long,
        val machineID: Long,
        val timestamp: Date
    ) {
        constructor(transactionModel: TransactionModel) : this(
            userID = transactionModel.userID,
            machineID = transactionModel.machineID,
            timestamp = transactionModel.timestamp
        )

        companion object {
            val mapFunction: (TransactionModel) -> TransactionDTO = { TransactionDTO(it) }
        }
    }

    /**
     * Gets all saved Transactions
     *
     * @return  list of [TransactionDTO], NO_CONTENT on empty list or Exception status
     * */
    @GetMapping("/coffee")
    fun getStats(): ResponseEntity<List<TransactionDTO>> = buildStatsResponse(
        transactionService.getTransactions(), mapFunction
    )

    /**
     * Gets all saved Transactions for given machine
     *
     * @param   machineID   ID of the machine to get the transactions for
     *
     * @return  list of [TransactionDTO], NO_CONTENT on empty list or Exception status
     * */
    @GetMapping("/coffee/machine/{machineID}")
    fun getStatsMachine(@PathVariable machineID: Long): ResponseEntity<List<TransactionDTO>> = buildStatsResponse(
        transactionService.getTransactionsMachine(machineID), mapFunction
    )

    /**
     * Gets all saved Transactions for given user
     *
     * @param   userID  ID of the user to get the transactions for
     *
     * @return  list of [TransactionDTO], NO_CONTENT on empty list or Exception status
     * */
    @GetMapping("/coffee/user/{userID}")
    fun getStatsUser(@PathVariable userID: Long): ResponseEntity<List<TransactionDTO>> = buildStatsResponse(
        transactionService.getTransactionsUser(userID), mapFunction
    )

    /**
     * Gets caffeine levels for given user. Levels are for past 24 hours - first item is the nearest hour.
     * If the response should be null (no caffeine level), zeros are returned. Numbers are rounded to 3 decimal places.
     *
     * @param   userID  ID of the user to get the levels for
     *
     * @return  list of caffeine level (double) or Exception status
     * */
    @GetMapping("/level/user/{userID}")
    fun getLevelUser(@PathVariable userID: Long): ResponseEntity<List<Double>> = buildStatsResponse(
        transactionService.computeUserCaffeineLevel(userID)
    ) { it }

    /**
     * Private helping method to build a status and handle empty lists
     * */
    private fun <T, V> buildStatsResponse(list: List<T>, mapFnc: (T) -> V): ResponseEntity<List<V>> = list
        .map(mapFnc)
        .let { transactions ->
            return if (transactions.isEmpty()) ResponseEntity.noContent().build()
            else ResponseEntity.ok(transactions)
        }
}