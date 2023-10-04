package com.mycrm.backendmycrm.controller

import com.mycrm.backendmycrm.DTO.ProjectDTO
import com.mycrm.backendmycrm.DTO.ProjectIdDTO
import com.mycrm.backendmycrm.model.Project
import com.mycrm.backendmycrm.model.Task
import com.mycrm.backendmycrm.model.User
import com.mycrm.backendmycrm.service.MyResourceNotFoundException
import com.mycrm.backendmycrm.service.ProjectsService
import com.mycrm.backendmycrm.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestBody


@RestController
@RequestMapping("/projects")
class ProjectsController(val userService: UserService, val projectsService: ProjectsService) {
    @GetMapping(value = ["/my"], produces = ["application/json"])
    fun findMyProducts(httpServletRequest: HttpServletRequest?): MutableIterable<Project?>? {

        return projectsService.findAllProducts()
    }

    @PostMapping(value = ["/add"], produces = ["application/json"])
    fun createProject(httpServletRequest: HttpServletRequest?, @RequestBody projectDTO: ProjectDTO): Project? {
        val customerOpt = userService.whoIs(httpServletRequest!!)
        System.out.println(customerOpt)
        if (customerOpt!!.isEmpty) {
            throw MyResourceNotFoundException("Ошибка авторизации")
        }
        val customer: User = customerOpt.get()
        return projectsService.createProject(projectDTO, customer)
    }

    @PostMapping(value = ["/tasks/get"], produces = ["application/json"])
    fun findTasks(@RequestBody projectIdDTO: ProjectIdDTO): List<Task?>? {

        return projectsService.findTasks(projectsService.findProject(projectIdDTO.project_id.toLong()))
    }

    @PostMapping(value = ["/tasks"], produces = ["application/json"])
    fun readyTask(@RequestParam("task_id") task_id: String): Task? {

        return projectsService.readyTask(projectsService.findTask(task_id.toLong()))
    }
}