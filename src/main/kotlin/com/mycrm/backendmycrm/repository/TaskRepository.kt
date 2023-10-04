package com.mycrm.backendmycrm.repository

import com.mycrm.backendmycrm.model.Project
import com.mycrm.backendmycrm.model.Task
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TaskRepository : CrudRepository<Task?, Long?> {
    fun findAllByProject(project: Optional<Project?>?): List<Task?>?
    fun findById(id: Long?): Task?
}