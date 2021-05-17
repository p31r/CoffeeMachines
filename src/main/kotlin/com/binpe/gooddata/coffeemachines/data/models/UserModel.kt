package com.binpe.gooddata.coffeemachines.data.models

import javax.persistence.*

@Entity
@Table(name = "users")
public class UserModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userID: Long? = null,
    val login: String,
    val password: String,
    val email: String
)
