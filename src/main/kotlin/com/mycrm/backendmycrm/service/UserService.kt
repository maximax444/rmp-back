package com.mycrm.backendmycrm.service

import com.mycrm.backendmycrm.DTO.UserDTO
import com.mycrm.backendmycrm.model.User
import com.mycrm.backendmycrm.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import java.util.*

@AllArgsConstructor
@Service("userService")
class UserService {

    @Autowired
    private val userRepository: UserRepository? = null

    fun getToken(username: String, password: String): String? {
        val user: Optional<User?>? = userRepository?.login(username, password)
        if (user != null) {
            if (user.isPresent) {
                val encoder = Base64.getEncoder()
                val token = String(encoder.encode(username.toByteArray())) +
                        ":" + String(encoder.encode(password.toByteArray()))
                val custom: User = user.get()
                custom.token = token
                userRepository!!.save(custom)
                return token
            }
        }
        return ""
    }

    fun registration(userDTO: UserDTO): User? {
        val customer = User()
        if (userDTO.login == null || userDTO.password == null) {
            throw MyResourceNotFoundException("Вы ошиблись, отсутствует имя или пароль")
        }
        customer.userName = userDTO.login
        customer.password = userDTO.password
        val res: Optional<User?>? = userRepository?.findByUserNameAndPassword(
            userDTO.login,
            userDTO.password
        )
        return res?.orElseGet { userRepository?.save(customer) }
    }

    fun whoIs(httpServletRequest: HttpServletRequest): Optional<User?>? {
        var token = if (httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION) != "") httpServletRequest.getHeader(
            HttpHeaders.AUTHORIZATION
        ) else ""
        System.out.println("toke")
        System.out.println(token)
        token = token.replace("Bearer".toRegex(), "").trim { it <= ' ' }
        System.out.println(token)
        return userRepository?.findByToken(token)
    }
}

class MyResourceNotFoundException(s: String) : Throwable() {

}
