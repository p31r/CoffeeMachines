package com.binpe.gooddata.coffeemachines.api.services

import com.binpe.gooddata.coffeemachines.api.IMachineService
import com.binpe.gooddata.coffeemachines.api.models.MachineCreate
import com.binpe.gooddata.coffeemachines.data.models.MachineModel
import com.binpe.gooddata.coffeemachines.data.repositories.MachineRepository
import com.github.michaelbull.result.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MachineService @Autowired constructor(
    val machineRepository: MachineRepository
) : IMachineService {
    override fun registerMachine(machineCreate: MachineCreate): Result<MachineModel, Throwable> {
        TODO("Not yet implemented")
    }
}