package com.binpe.gooddata.coffeemachines.api

import com.binpe.gooddata.coffeemachines.data.models.UserModel
import org.springframework.stereotype.Component

@Component
interface IUserService {
    fun createUser(userModel: UserModel): UserModel

    fun userExists(userID: Long): Boolean
}