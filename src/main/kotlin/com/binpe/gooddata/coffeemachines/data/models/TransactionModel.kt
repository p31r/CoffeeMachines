package com.binpe.gooddata.coffeemachines.data.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "purchases")
public class TransactionModel(
    @Id
    @GeneratedValue
    val purchaseID: Long? = null,
    val userID: Long,
    val machineID: Long,
    val timestamp: Date
)
