package com.mycrm.backendmycrm.repository

import com.mycrm.backendmycrm.model.Project
import com.mycrm.backendmycrm.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectsRepository : CrudRepository<Project?, Long?> {
    fun findAllByUser(user: User?): List<Project?>?
    fun findById(id: Long?): Project?
}