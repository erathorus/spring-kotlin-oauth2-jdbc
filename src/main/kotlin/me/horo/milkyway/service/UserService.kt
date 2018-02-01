package me.horo.milkyway.service

import me.horo.milkyway.domain.User

interface UserService {
    fun create(user: User): User
    fun findByUsername(username: String): User
}