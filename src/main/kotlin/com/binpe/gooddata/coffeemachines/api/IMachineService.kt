package com.binpe.gooddata.coffeemachines.api

import com.binpe.gooddata.coffeemachines.data.models.MachineModel
import org.springframework.stereotype.Component

@Component
interface IMachineService {
    fun registerMachine(machineModel: MachineModel): MachineModel

    fun machineExists(machineID: Long): Boolean
}