package com.binpe.gooddata.coffeemachines.data.models

import javax.persistence.*

/**
 * Main ENTITY for User as database model, have it's own table
 * Parameter restrictions:
 *  - id - is auto-generated
 *  - login - non-null, non-empty, at least 4 characters, unique; all checks in the service
 *  - password - non-null, non-empty, at least 4 characters, just plain text ...
 *  - email - non-null, non-empty, at least 4 characters, unique; all checks in the service
 *
 * @param   id          unique, auto-generated primary key of the object
 * @param   login       unique user name
 * @param   password    user password in simple text (not secured)
 * @param   email       unique user e-mail
 * */
@Entity
@Table(name = "USERS")
data class UserModel(
    @field:Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val login: String,
    val password: String,
    val email: String
)