package com.binpe.gooddata.coffeemachines.api.controllers

import com.binpe.gooddata.coffeemachines.api.IUserService
import com.binpe.gooddata.coffeemachines.api.errors.ArgumentException
import com.binpe.gooddata.coffeemachines.api.utils.ValidatorUtils.isEmailValid
import com.binpe.gooddata.coffeemachines.api.utils.ValidatorUtils.isLoginValid
import com.binpe.gooddata.coffeemachines.api.utils.ValidatorUtils.isPasswordValid
import com.binpe.gooddata.coffeemachines.data.models.UserModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/user"], produces = [MimeTypeUtils.APPLICATION_JSON_VALUE])
class UserController @Autowired constructor(
    val userService: IUserService
) {
    @PostMapping("/request")
    fun createUser(@RequestBody userModel: UserModel): ResponseEntity<Long> {
        if (!isLoginValid(userModel.login)) throw ArgumentException("Invalid user login")
        if (!isPasswordValid(userModel.password)) throw ArgumentException("Invalid user password")
        if (!isEmailValid(userModel.email)) throw ArgumentException("Invalid user email")

        val createdUserModel = userService.createUser(userModel)
        return ResponseEntity.ok(createdUserModel.id)
    }
}