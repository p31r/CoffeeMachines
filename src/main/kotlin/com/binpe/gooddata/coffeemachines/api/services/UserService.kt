package com.binpe.gooddata.coffeemachines.api.services

import com.binpe.gooddata.coffeemachines.api.IUserService
import com.binpe.gooddata.coffeemachines.api.errors.ServiceException
import com.binpe.gooddata.coffeemachines.data.models.UserModel
import com.binpe.gooddata.coffeemachines.data.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    val userRepository: UserRepository
) : IUserService {
    override fun createUser(userModel: UserModel): UserModel {
        if (!isLoginUnique(userModel.login)) throw ServiceException("Login is not unique")
        if (!isEmailUnique(userModel.email)) throw ServiceException("E-mail is not unique")
        return userRepository.save(userModel)
    }

    override fun userExists(userID: Long): Boolean = userRepository.existsById(userID)

    private fun isLoginUnique(login: String): Boolean = !userRepository.existsByLogin(login)
    private fun isEmailUnique(email: String): Boolean = !userRepository.existsByEmail(email)
}