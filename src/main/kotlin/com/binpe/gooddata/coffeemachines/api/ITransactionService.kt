package com.binpe.gooddata.coffeemachines.api

import org.joda.time.DateTime
import org.springframework.stereotype.Component

@Component
interface ITransactionService {
    fun registerTransaction(userID: Long, machineID: Long, buyTime: DateTime)

    fun registerTransaction(userID: Long, machineID: Long)

    fun getTransactions()
}