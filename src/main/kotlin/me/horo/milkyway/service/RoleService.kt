package me.horo.milkyway.service

import me.horo.milkyway.domain.Role

interface RoleService {
    fun findByName(name: String): Role
}