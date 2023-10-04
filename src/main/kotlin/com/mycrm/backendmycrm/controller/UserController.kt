package com.mycrm.backendmycrm.controller

import com.mycrm.backendmycrm.DTO.UserDTO
import com.mycrm.backendmycrm.model.User
import com.mycrm.backendmycrm.service.UserService
import lombok.AllArgsConstructor
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @GetMapping("/registration")
    fun createNewUser(@RequestParam("login") login: String, @RequestParam("password") pass: String): User? {
        return userService.registration(UserDTO(login, pass))
    }

    @PostMapping("/login", produces = ["application/json"])
    fun login(@RequestBody user: UserDTO) : String? {
//        val curr = User(login, pass)
        System.out.println(user.password)
        val token = userService.getToken(user.login, user.password)
        return if (token == "") {
            "no token found"
        } else token
    }
}