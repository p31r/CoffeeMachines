package com.binpe.gooddata.coffeemachines.data.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "TRANSACTIONS")
data class TransactionModel(
    @field:Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val userID: Long,
    val machineID: Long,
    val timestamp: Date
)
