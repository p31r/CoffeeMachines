package com.binpe.gooddata.coffeemachines.api.controllers

import com.binpe.gooddata.coffeemachines.api.IUserService
import com.binpe.gooddata.coffeemachines.api.errors.ArgumentException
import com.binpe.gooddata.coffeemachines.api.utils.ValidatorUtils.isEmailValid
import com.binpe.gooddata.coffeemachines.api.utils.ValidatorUtils.isLoginValid
import com.binpe.gooddata.coffeemachines.api.utils.ValidatorUtils.isPasswordValid
import com.binpe.gooddata.coffeemachines.data.models.UserModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Basic AP for User handling - for now supports only creation of a new user
 * Custom Exceptions are resolved by [com.binpe.gooddata.coffeemachines.api.errors.RestResponseEntityExceptionHandler]
 *
 * @author  Petr Binčík
 * @version 1.0
 * */
@RestController
@RequestMapping(path = ["/user"], produces = [MimeTypeUtils.APPLICATION_JSON_VALUE])
class UserController @Autowired constructor(
    val userService: IUserService
) {
    /**
     * Creates new user.
     * Method checks:
     *  - login - not null, not empty, at least 4 characters, unique
     *  - password - not null, not empty, at least 4 characters
     *  - e-mail - not null, not empty, matched against regex, unique
     *
     * Note: changed to POST from PUT (don't like having create resource with PUT)
     * Note 2: won't implement encryption of the password now (yeah, I don't like plain text in the database too)
     * Encryption hints: https://www.baeldung.com/java-password-hashing
     *
     * @param   userModel   user model to be created, see [UserModel]
     *
     * @return  ID of the user (long) or custom Exception status
     * */
    @PostMapping("/request")
    fun createUser(@RequestBody userModel: UserModel): ResponseEntity<Long> {
        if (!isLoginValid(userModel.login)) throw ArgumentException("Invalid user login")
        if (!isPasswordValid(userModel.password)) throw ArgumentException("Invalid user password")
        if (!isEmailValid(userModel.email)) throw ArgumentException("Invalid user email")

        val createdUserModel = userService.createUser(userModel)
        return ResponseEntity.ok(createdUserModel.id)
    }
}