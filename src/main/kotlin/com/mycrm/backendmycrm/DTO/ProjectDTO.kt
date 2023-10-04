package com.mycrm.backendmycrm.DTO

class ProjectDTO(
    var title: String = "",
    var descr: String = "",
    var status: String = "",
    var user: UserDTO? = null
)