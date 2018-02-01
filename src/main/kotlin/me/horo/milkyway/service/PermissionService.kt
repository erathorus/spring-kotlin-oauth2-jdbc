package me.horo.milkyway.service

import me.horo.milkyway.domain.Permission

interface PermissionService {
    fun findByName(name: String): Permission
    fun create(name: String): Permission
}