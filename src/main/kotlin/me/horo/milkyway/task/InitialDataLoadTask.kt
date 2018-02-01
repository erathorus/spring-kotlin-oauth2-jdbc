package me.horo.milkyway.task

import me.horo.milkyway.service.PermissionService
import me.horo.milkyway.service.RoleService
import me.horo.milkyway.service.UserService
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class InitialDataLoadTask(
        private val userService: UserService,
        private val roleService: RoleService,
        private val permissionService: PermissionService
) : ApplicationListener<ContextRefreshedEvent> {
    private val alreadySetup = false

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) return

    }
}