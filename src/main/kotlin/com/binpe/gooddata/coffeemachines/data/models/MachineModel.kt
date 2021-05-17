package com.binpe.gooddata.coffeemachines.data.models

import javax.persistence.*

@Entity
@Table(name = "machines")
public class MachineModel(
    @Id
    @GeneratedValue
    val machineID: Long? = null,
    val caffeine: Double
)
