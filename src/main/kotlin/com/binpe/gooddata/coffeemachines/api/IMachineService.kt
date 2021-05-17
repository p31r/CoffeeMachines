package com.binpe.gooddata.coffeemachines.api

import com.binpe.gooddata.coffeemachines.api.models.MachineCreate
import com.binpe.gooddata.coffeemachines.data.models.MachineModel
import com.github.michaelbull.result.Result
import org.springframework.stereotype.Component

@Component
interface IMachineService {
    fun registerMachine(machineCreate: MachineCreate): Result<MachineModel, Throwable>
}