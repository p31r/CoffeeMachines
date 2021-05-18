package com.binpe.gooddata.coffeemachines.api.controllers

import com.binpe.gooddata.coffeemachines.api.ITransactionService
import com.binpe.gooddata.coffeemachines.api.errors.ArgumentException
import com.binpe.gooddata.coffeemachines.data.models.TransactionModel
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Basic AP for Coffee handling - for now supports only purchase of coffee for user
 * Custom Exceptions are resolved by [com.binpe.gooddata.coffeemachines.api.errors.RestResponseEntityExceptionHandler]
 *
 * @author  Petr Binčík
 * @version 1.0
 * */
@RestController
@RequestMapping(path = ["/coffee"], produces = [MimeTypeUtils.APPLICATION_JSON_VALUE])
class CoffeeController @Autowired constructor(
    val transactionService: ITransactionService
) {
    /**
     * Helping DTO for purchaseCoffee POST
     *
     * @param   timestamp   ISO-8601 string format of purchase time
     * */
    data class CoffeePurchaseDTO(val timestamp: String)

    /**
     * Create transaction of user for buying coffee from machine
     *
     * @param   userID      ID of the user
     * @param   machineID   ID of the machine
     * @param   dto         optional body with timestamp of the purchase
     *
     * @return  empty default ResponseEntity or Exception status
     * */
    @PutMapping("/buy/{userID}/{machineID}")
    fun purchaseCoffee(
        @PathVariable userID: Long,
        @PathVariable machineID: Long,
        @RequestBody(required = false) dto: CoffeePurchaseDTO? = null
    ): ResponseEntity<Unit> {
        val actualTime = DateTime.now()
        val timestamp = (dto?.timestamp?.let { DateTime(it) } ?: actualTime).takeIf {
            !it.isAfter(actualTime)
        } ?: throw ArgumentException("Invalid timestamp")

        val transactionModel = createTransactionModel(userID, machineID, timestamp.toDate())
        transactionService.registerTransaction(transactionModel)

        return ResponseEntity.ok().build()
    }

    private fun createTransactionModel(
        userID: Long, machineID: Long, timestamp: Date
    ): TransactionModel = TransactionModel(
        userID = userID,
        machineID = machineID,
        timestamp = timestamp
    )
}