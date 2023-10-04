package com.mycrm.backendmycrm.DTO

class TaskDTO(
    var title: String = "",
    var descr: String = "",
    var status: String = "",
    var project: ProjectDTO? = null
)