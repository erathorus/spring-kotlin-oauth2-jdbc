package me.horo.milkyway.service

import me.horo.milkyway.domain.Permission
import java.util.*

interface PermissionService {
    fun tryCreate(permission: Permission): Optional<Permission>
    fun findByName(name: String): Optional<Permission>
}