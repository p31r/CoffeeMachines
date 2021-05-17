package com.binpe.gooddata.coffeemachines.api.controllers

import com.binpe.gooddata.coffeemachines.api.ITransactionService
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
    @PutMapping("/buy/{userID}/{machineID}")
    fun purchaseCofee(
        @PathVariable userID: Long,
        @PathVariable machineID: Long,
        @RequestParam(required = false) timestamp: Date? = null
    ): ResponseEntity<Unit> {
        TODO("not yet implemented")
    }
}