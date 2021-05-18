package com.binpe.gooddata.coffeemachines.api

import com.binpe.gooddata.coffeemachines.data.models.TransactionModel
import org.springframework.stereotype.Component

/**
 * Service for Transactions handling and statistics. Is used mainly by StatsController and CoffeeController.
 * Implemented for now:
 *  - registerTransaction
 *  - getTransactions
 *  - getTransactionsUser
 *  - getTransactionsMachine
 *  - computeUserCaffeineLevel
 *
 * Note: computing should be moved into separate logic
 *
 * @author  Petr Binčík
 * @version 1.0
 * */
@Component
interface ITransactionService {
    /**
     * Creates new purchase/transaction for given user, machine and time
     *
     * @param   transactionModel    [TransactionModel] object to create transaction for
     * */
    fun registerTransaction(transactionModel: TransactionModel)

    /**
     * Retrieves all saved transactions
     *
     * @return  list of saved transactions or empty list
     * */
    fun getTransactions(): List<TransactionModel>

    /**
     * Retrieves all saved transactions for given user
     *
     * @param   userID  ID of the user to get the transactions for
     *
     * @return  list of saved transactions or empty list
     * */
    fun getTransactionsUser(userID: Long): List<TransactionModel>

    /**
     * Retrieves all saved transactions for given machine
     *
     * @param   machineID   ID of the machine to get the transactions for
     *
     * @return  list of saved transactions or empty list
     * */
    fun getTransactionsMachine(machineID: Long): List<TransactionModel>

    // TODO move to separate service
    /**
     * Compute actual caffeine level for given user for previous 24 hours. First index is the most recent hour.
     *
     * @param   userID  ID of the user to compute caffeine for
     *
     * @return  list with size of 24 for each hour, never empty, default is zero as double
     * */
    fun computeUserCaffeineLevel(userID: Long): List<Double>
}