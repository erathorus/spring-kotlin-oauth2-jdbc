package me.horo.milkyway.service

import me.horo.milkyway.domain.Role
import java.util.*

interface RoleService {
    fun tryCreate(role: Role): Optional<Role>
    fun findByName(name: String): Optional<Role>
}