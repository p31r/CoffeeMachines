package com.binpe.gooddata.coffeemachines.api

import com.binpe.gooddata.coffeemachines.data.models.MachineModel
import org.springframework.stereotype.Component

/**
 * Service for Machine handling. Is used mainly by MachineController.
 * Implemented for now:
 *  - registerMachine
 *  - getMachineByIDOrThrow
 *  - machineExists
 *
 * @author  Petr Binčík
 * @version 1.0
 * */
@Component
interface IMachineService {
    /**
     * Creates new machine for given model
     *
     * @param   machineModel    [MachineModel] object for new machine
     *
     * @return  newly created machine or throws exception
     * */
    fun registerMachine(machineModel: MachineModel): MachineModel

    /**
     * Reatrieves machine by it's ID or throws exception
     *
     * @param   machineID   specific ID to get machine for
     *
     * @return  saved machine or throws exception
     * */
    fun getMachineByIDOrThrow(machineID: Long): MachineModel

    /**
     * Checks if machine already exists
     *
     * @param   machineID   ID of the machine to check
     *
     * @return  true if machine exists
     * */
    fun machineExists(machineID: Long): Boolean
}