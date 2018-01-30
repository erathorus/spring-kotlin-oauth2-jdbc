package me.horo.blog.service

import me.horo.blog.domain.User

interface UserService {
    fun create(user: User): User
}