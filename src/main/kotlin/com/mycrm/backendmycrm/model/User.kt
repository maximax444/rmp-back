package com.mycrm.backendmycrm.model

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import lombok.ToString
import java.util.*

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    open var id: Long? = null

    @Column(name = "userName")
    var userName: String = ""

    @Column(name = "password")
    var password: String = ""

    @ToString.Exclude
    @Column(name = "token")
    var token: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (userName != other.userName) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userName?.hashCode() ?: 0
        result = 31 * result + (password?.hashCode() ?: 0)
        return result
    }



}