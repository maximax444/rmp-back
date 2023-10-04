package com.mycrm.backendmycrm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class BackendMycrmApplication

fun main(args: Array<String>) {
    runApplication<BackendMycrmApplication>(*args)
}
