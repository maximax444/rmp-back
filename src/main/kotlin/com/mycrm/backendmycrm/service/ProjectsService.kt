package com.mycrm.backendmycrm.service

import com.mycrm.backendmycrm.DTO.ProjectDTO
import com.mycrm.backendmycrm.model.Project
import com.mycrm.backendmycrm.model.Task
import com.mycrm.backendmycrm.model.User
import com.mycrm.backendmycrm.repository.ProjectsRepository
import com.mycrm.backendmycrm.repository.TaskRepository
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@AllArgsConstructor
@Service("projectsService")
class ProjectsService {

    @Autowired
    private val projectRepository: ProjectsRepository? = null

    @Autowired
    private val taskRepository: TaskRepository? = null

    fun findMyProducts(user: User?): List<Project?>? {
        return projectRepository?.findAllByUser(user)
    }

    fun findAllProducts(): MutableIterable<Project?>? {
        return projectRepository?.findAll()
    }

    fun findTasks(project: Optional<Project?>?): List<Task?>? {
        return taskRepository?.findAllByProject(project)
    }

    fun findProject(proj_id: Long): Optional<Project?>? {
        return projectRepository?.findById(proj_id)
    }

    fun findTask(task_id: Long): Optional<Task?>? {
        return taskRepository?.findById(task_id)
    }

    @Transactional
    fun createProject(projectDTO: ProjectDTO, customer: User?): Project? {
        val product = Project()
        product.user = customer
        product.title= projectDTO.title
        product.descr= projectDTO.descr
        product.status= projectDTO.status
        return projectRepository?.save(product)
    }

    fun readyTask(task: Optional<Task?>?): Task? {
        var taskk = task?.get()
        taskk!!.status = "1"
        return taskRepository?.save(taskk)
    }
}