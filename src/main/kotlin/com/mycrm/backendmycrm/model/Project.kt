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
@Table(name = "projects")
class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    open var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null

    @Column(name = "title")
    var title: String = ""

    @Column(name = "descr")
    var descr: String = ""

    @Column(name = "status")
    var status: String = "0"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Project

        if (user != other.user) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        return result
    }


}