package com.binpe.gooddata.coffeemachines.data.models

import java.util.*
import javax.persistence.*

/**
 * Main ENTITY for Transaction as database model, have it's own table
 * Parameter restrictions:
 *  - id - is auto-generated
 *  - userID - saved unique ID of the user, no restriction here
 *  - machineID - saved unique ID of the machine, no restriction here
 *  - timestamp - check are made in Service, should be ISO-8601 timestamp
 *
 * @param   id          unique, auto-generated primary key of the object
 * @param   userID      ID of the user the transaction was made by
 * @param   machineID   ID of the machine the transaction was made for
 * @param   timestamp   specific time of the transaction
 * */
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
