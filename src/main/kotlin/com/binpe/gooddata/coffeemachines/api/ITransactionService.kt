package com.binpe.gooddata.coffeemachines.api

import com.binpe.gooddata.coffeemachines.data.models.TransactionModel
import org.springframework.stereotype.Component

@Component
interface ITransactionService {
    fun registerTransaction(transactionModel: TransactionModel)

    fun getTransactions(): List<TransactionModel>

    fun getTransactionsUser(userID: Long): List<TransactionModel>

    fun getTransactionsMachine(machineID: Long): List<TransactionModel>

    // TODO move to separate service
    fun computeUserCaffeineLevel(userID: Long): List<Double>
}