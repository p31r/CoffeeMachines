package com.binpe.gooddata.coffeemachines.api.services

import com.binpe.gooddata.coffeemachines.api.IMachineService
import com.binpe.gooddata.coffeemachines.api.ITransactionService
import com.binpe.gooddata.coffeemachines.api.IUserService
import com.binpe.gooddata.coffeemachines.api.errors.ServiceException
import com.binpe.gooddata.coffeemachines.data.models.TransactionModel
import com.binpe.gooddata.coffeemachines.data.repositories.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService @Autowired constructor(
    val transactionRepository: TransactionRepository,
    val userService: IUserService,
    val machineService: IMachineService
) : ITransactionService {
    override fun registerTransaction(transactionModel: TransactionModel) {
        if (!userService.userExists(transactionModel.userID)) throw ServiceException("User does not exist")
        if (!machineService.machineExists(transactionModel.machineID)) throw ServiceException("Machine does not exist")

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
        TODO("Not yet implemented")
    }
}