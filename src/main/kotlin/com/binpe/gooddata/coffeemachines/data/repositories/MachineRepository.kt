package com.binpe.gooddata.coffeemachines.data.repositories

import com.binpe.gooddata.coffeemachines.data.models.MachineModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MachineRepository : CrudRepository<MachineModel, Long>