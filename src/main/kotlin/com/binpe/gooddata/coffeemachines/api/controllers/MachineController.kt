package com.binpe.gooddata.coffeemachines.api.controllers

import com.binpe.gooddata.coffeemachines.api.IMachineService
import com.binpe.gooddata.coffeemachines.api.errors.ArgumentException
import com.binpe.gooddata.coffeemachines.api.utils.ValidatorUtils.isMachineCaffeineValid
import com.binpe.gooddata.coffeemachines.api.utils.ValidatorUtils.isMachineNameValid
import com.binpe.gooddata.coffeemachines.data.models.MachineModel
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
    @PostMapping
    fun registerMachine(@RequestBody machineModel: MachineModel): ResponseEntity<Long> {
        if(!isMachineNameValid(machineModel.name)) throw ArgumentException("Machine name is invalid")
        if(!isMachineCaffeineValid(machineModel.caffeine)) throw ArgumentException("Machine caffeine is invalid")

        val createdMachine = machineService.registerMachine(machineModel)
        return ResponseEntity.ok(createdMachine.id)
    }
}