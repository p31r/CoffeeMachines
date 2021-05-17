package com.binpe.gooddata.coffeemachines.api.controllers

import com.binpe.gooddata.coffeemachines.api.IUserService
import org.springframework.beans.factory.annotation.Autowired
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
    data class UserCreateDTO(
        val login: String? = null,
        val password: String? = null,
        val email: String? = null
    )

    @PostMapping("/request")
    fun createUser(@RequestBody createUser: UserCreateDTO): ResponseEntity<Long> {
        TODO("not yet implemented")
    }
}