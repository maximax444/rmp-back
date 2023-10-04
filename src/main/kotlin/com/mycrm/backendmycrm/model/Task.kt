package com.mycrm.backendmycrm.model

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Entity
@Getter
@Setter
@ToString
@Table(name = "tasks")
class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    open var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "project_id")
    var project: Project? = null

    @Column(name = "title")
    var title: String = ""

    @Column(name = "descr")
    var descr: String = ""

    @Column(name = "status")
    var status: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (project != other.project) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = project?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        return result
    }


}