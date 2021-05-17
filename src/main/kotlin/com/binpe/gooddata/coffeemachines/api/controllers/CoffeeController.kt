package com.binpe.gooddata.coffeemachines.api.controllers

import com.binpe.gooddata.coffeemachines.api.ITransactionService
import com.binpe.gooddata.coffeemachines.data.models.TransactionModel
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/coffee"], produces = [MimeTypeUtils.APPLICATION_JSON_VALUE])
class CoffeeController @Autowired constructor(
    val transactionService: ITransactionService
) {
    data class CoffeePurchaseDTO(val timestamp: String)

    @PutMapping("/buy/{userID}/{machineID}")
    fun purchaseCofee(
        @PathVariable userID: Long,
        @PathVariable machineID: Long,
        @RequestBody(required = false) dto: CoffeePurchaseDTO? = null
    ): ResponseEntity<Unit> {
        val transactionModel = createTransactionModel(userID, machineID, dto?.timestamp)
        transactionService.registerTransaction(transactionModel)

        return ResponseEntity.ok().build()
    }

    private fun createTransactionModel(
        userID: Long, machineID: Long, timestamp: String?
    ): TransactionModel = TransactionModel(
        userID = userID,
        machineID = machineID,
        timestamp = timestamp?.let { DateTime(it).toDate() } ?: Date()
    )
}