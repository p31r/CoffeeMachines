package com.binpe.gooddata.coffeemachines.api.models

data class UserCreate(
    val login: String,
    val password: String,
    val email: String
)
