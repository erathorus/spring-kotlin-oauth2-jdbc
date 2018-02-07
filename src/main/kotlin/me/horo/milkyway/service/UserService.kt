package me.horo.milkyway.service

import me.horo.milkyway.domain.User
import java.util.*

interface UserService {
    fun tryCreate(user: User): Optional<User>
    fun findByUsername(username: String): Optional<User>
}