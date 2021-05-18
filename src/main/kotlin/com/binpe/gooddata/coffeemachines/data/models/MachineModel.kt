package com.binpe.gooddata.coffeemachines.data.models

import javax.persistence.*

/**
 * Main ENTITY for Machine as database model, have it's own table
 * Parameter restrictions:
 *  - id - is auto-generated
 *  - name - no special treatment, for simplicity is used as non-null
 *  - caffeine - no special treatment, for simplicity is used as non-null
 *
 * @param   id          unique, auto-generated primary key of the object
 * @param   name        name of the machine
 * @param   caffeine    how much milligrams of caffeine have one cup from this machine
 * */
@Entity
@Table(name = "MACHINES")
data class MachineModel(
    @field:Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    val caffeine: Int
)
