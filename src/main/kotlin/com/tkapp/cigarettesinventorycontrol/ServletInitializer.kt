package com.tkapp.cigarettesinventorycontrol

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class ServletInitializer: SpringBootServletInitializer() {

    fun main(args: Array<String>) {
        SpringApplication.run(ServletInitializer::class.java, *args)
    }

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(ServletInitializer::class.java)
    }
}