package me.horo.milkyway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
class MilkyWayApplication

fun main(args: Array<String>) {
    runApplication<MilkyWayApplication>(*args)
}