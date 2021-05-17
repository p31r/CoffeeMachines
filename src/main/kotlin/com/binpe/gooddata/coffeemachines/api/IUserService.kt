package com.binpe.gooddata.coffeemachines.api

import com.binpe.gooddata.coffeemachines.api.models.UserCreate
import com.binpe.gooddata.coffeemachines.data.models.UserModel
import com.github.michaelbull.result.Result
import org.springframework.stereotype.Component

@Component
interface IUserService {
    fun createUser(createUser: UserCreate): Result<UserModel, Throwable>
}