package com.binpe.gooddata.coffeemachines.data.repositories

import com.binpe.gooddata.coffeemachines.data.models.UserModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserModel, Long> {
    fun existsByEmail(email: String): Boolean
    fun existsByLogin(login: String): Boolean
}