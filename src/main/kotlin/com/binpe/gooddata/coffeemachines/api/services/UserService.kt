package com.binpe.gooddata.coffeemachines.api.services

import com.binpe.gooddata.coffeemachines.api.IUserService
import com.binpe.gooddata.coffeemachines.api.models.UserCreate
import com.binpe.gooddata.coffeemachines.data.models.UserModel
import com.binpe.gooddata.coffeemachines.data.repositories.UserRepository
import com.github.michaelbull.result.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    val userRepository: UserRepository
) : IUserService {
    override fun createUser(createUser: UserCreate): Result<UserModel, Throwable> {
        TODO("Not yet implemented")
    }
}