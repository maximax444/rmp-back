package com.mycrm.backendmycrm.repository

import com.mycrm.backendmycrm.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User?, Long?> {
    @Query(value = "SELECT u FROM User u where u.userName = ?1 and u.password = ?2 ")
    fun login(username: String?, password: String?): Optional<User?>?
    fun findByToken(token: String?): Optional<User?>?
    fun findByUserNameAndPassword(userName: String?, password: String?): Optional<User?>?
}