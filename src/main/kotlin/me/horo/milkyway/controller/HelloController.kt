package me.horo.milkyway.controller

import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HelloController {
    @RequestMapping("/hello")
    fun index(): String {
        return "Hello!"
    }
}