package com.binpe.gooddata.coffeemachines.api.services

import com.binpe.gooddata.coffeemachines.api.ITransactionService
import org.joda.time.DateTime
import org.springframework.stereotype.Service

@Service
class TransactionService : ITransactionService {
    override fun registerTransaction(userID: Long, machineID: Long, buyTime: DateTime) {
        TODO("Not yet implemented")
    }

    override fun registerTransaction(userID: Long, machineID: Long) {
        //registerCoffee(userID, machineID, DateTime.now())
        TODO("Not yet implemented")
    }

    override fun getTransactions() {
        TODO("Not yet implemented")
    }

    override fun getTransactionsUser(userID: Long) {
        TODO("Not yet implemented")
    }

    override fun getTransactionsMachine(machineID: Long) {
        TODO("Not yet implemented")
    }
}