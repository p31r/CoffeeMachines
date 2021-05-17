package com.binpe.gooddata.coffeemachines.api.controllers

import com.binpe.gooddata.coffeemachines.api.ITransactionService
import com.binpe.gooddata.coffeemachines.data.models.TransactionModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(path = ["/stats"], produces = [MimeTypeUtils.APPLICATION_JSON_VALUE])
class StatsController @Autowired constructor(
    val transactionService: ITransactionService
) {
    data class TransactionDTO(
        val machineID: Long,
        val userID: Long,
        val timestamp: Date
    ) {
        constructor(transactionModel: TransactionModel) : this(
            machineID = transactionModel.machineID,
            userID = transactionModel.userID,
            timestamp = transactionModel.timestamp
        )
    }

    @GetMapping("/coffee")
    fun getStats(): ResponseEntity<List<TransactionDTO>> = buildStatsResponse(
        transactionService.getTransactions()
    )

    @GetMapping("/coffee/machine/{machineID}")
    fun getStatsMachine(@PathVariable machineID: Long): ResponseEntity<List<TransactionDTO>> = buildStatsResponse(
        transactionService.getTransactionsMachine(machineID)
    )

    @GetMapping("/coffee/user/{userID}")
    fun getStatsUser(@PathVariable userID: Long): ResponseEntity<List<TransactionDTO>> = buildStatsResponse(
        transactionService.getTransactionsUser(userID)
    )

    @GetMapping("/level/user/{userID}")
    fun getLevelUser(@PathVariable userID: Long): ResponseEntity<List<Float>> {
        TODO("not yet implemented")
    }

    private fun buildStatsResponse(list: List<TransactionModel>): ResponseEntity<List<TransactionDTO>> = list
        .map(::TransactionDTO)
        .let { transactions ->
            return if (transactions.isEmpty()) ResponseEntity.noContent().build()
            else ResponseEntity.ok(transactions)
        }

}