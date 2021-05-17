package com.binpe.gooddata.coffeemachines.data.models

import javax.persistence.*

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