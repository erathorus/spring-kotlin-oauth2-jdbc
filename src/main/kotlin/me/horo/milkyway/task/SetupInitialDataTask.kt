package me.horo.milkyway.task

import me.horo.milkyway.domain.Permission
import me.horo.milkyway.domain.Role
import me.horo.milkyway.domain.User
import me.horo.milkyway.service.PermissionService
import me.horo.milkyway.service.RoleService
import me.horo.milkyway.service.UserService
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SetupUserDataTask(
        private val userService: UserService,
        private val roleService: RoleService,
        private val permissionService: PermissionService
) : ApplicationListener<ContextRefreshedEvent> {
    private var alreadySetup = false

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) return
        alreadySetup = true

        val readPermission = createPermissionIfNotFound("READ_PERMISSION")
        val writePermission = createPermissionIfNotFound("WRITE_PERMISSION")

        val adminRole = createRoleIfNotFound("ROLE_ADMIN", hashSetOf(readPermission, writePermission))
        val userRole = createRoleIfNotFound("ROLE_USER", hashSetOf(readPermission))

        createUserIfNotFound("user1", "abcd1234", hashSetOf(adminRole, userRole))
        createUserIfNotFound("user2", "123456", hashSetOf(userRole))
    }

    @Transactional
    fun createPermissionIfNotFound(name: String): Permission {
        val permission = Permission(name = name)
        var retrieval = permissionService.tryCreate(permission)
        if (!retrieval.isPresent)
            retrieval = permissionService.findByName(name)
        return retrieval.get()
    }

    @Transactional
    fun createRoleIfNotFound(name: String, permissions: Set<Permission>): Role {
        val role = Role(name = name, permissions = permissions)
        var retrieval = roleService.tryCreate(role)
        if (!retrieval.isPresent)
            retrieval = roleService.findByName(name)
        return retrieval.get()
    }

    @Transactional
    fun createUserIfNotFound(username: String, password: String, roles: Set<Role>): User {
        val user = User(username = username, password = password, roles = roles)
        var retrieval = userService.tryCreate(user)
        if (!retrieval.isPresent)
            retrieval = userService.findByUsername(username)
        return retrieval.get()
    }
}