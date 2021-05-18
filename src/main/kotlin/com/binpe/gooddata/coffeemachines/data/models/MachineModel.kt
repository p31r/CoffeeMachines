package com.binpe.gooddata.coffeemachines.data.models

import javax.persistence.*

@Entity
@Table(name = "MACHINES")
data class MachineModel(
    @field:Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    val caffeine: Int
)
