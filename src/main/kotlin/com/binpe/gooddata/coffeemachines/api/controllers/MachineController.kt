package com.binpe.gooddata.coffeemachines.api.controllers

import com.binpe.gooddata.coffeemachines.api.IMachineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/machine"], produces = [MimeTypeUtils.APPLICATION_JSON_VALUE])
class MachineController @Autowired constructor(
    val machineService: IMachineService
) {
    data class MachineCreateDTO(
        val name: String? = null,
        val caffeine: Double? = null
    )

    @PostMapping
    fun registerMachine(@RequestBody createMachine: MachineCreateDTO): ResponseEntity<Long> {
        TODO("not yet implemented")
    }
}