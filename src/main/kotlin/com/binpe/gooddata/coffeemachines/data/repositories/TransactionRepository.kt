package com.binpe.gooddata.coffeemachines.data.repositories

import com.binpe.gooddata.coffeemachines.data.models.TransactionModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : CrudRepository<TransactionModel, Long> {
    fun findAllByMachineID(machineID: Long): Iterable<TransactionModel>
    fun findAllByUserID(userID: Long): Iterable<TransactionModel>
}