package com.binpe.gooddata.coffeemachines.api.services

import com.binpe.gooddata.coffeemachines.api.IMachineService
import com.binpe.gooddata.coffeemachines.data.models.MachineModel
import com.binpe.gooddata.coffeemachines.data.repositories.MachineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MachineService @Autowired constructor(
    val machineRepository: MachineRepository
) : IMachineService {
    override fun registerMachine(machineModel: MachineModel): MachineModel = machineRepository.save(machineModel)

    override fun getMachineByIDOrThrow(machineID: Long): MachineModel = machineRepository
        .findById(machineID)
        .orElseThrow()

    override fun machineExists(machineID: Long): Boolean = machineRepository.existsById(machineID)
}