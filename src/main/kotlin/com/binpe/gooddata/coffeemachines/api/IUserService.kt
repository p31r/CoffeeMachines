package com.binpe.gooddata.coffeemachines.api

import com.binpe.gooddata.coffeemachines.data.models.UserModel
import org.springframework.stereotype.Component

/**
 * Service for User handling. Is used mainly by UserController.
 * Implemented for now:
 *  - createUser
 *  - userExists
 *
 * @author  Petr Binčík
 * @version 1.0
 * */
@Component
interface IUserService {
    /**
     * Create new user for given model
     *
     * @param   userModel   [UserModel] object for new user
     *
     * @return  newly created user or throws exceptions
     * */
    fun createUser(userModel: UserModel): UserModel

    /**
     * Checks if user for given ID already exists
     *
     * @param   userID  ID of the user to check
     *
     * @return  true if user already exists
     * */
    fun userExists(userID: Long): Boolean
}