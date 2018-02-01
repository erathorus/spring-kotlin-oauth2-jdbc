package me.horo.milkyway.service

import me.horo.milkyway.domain.Permission
import me.horo.milkyway.repository.PermissionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PermissionServiceImpl(
        private val repo: PermissionRepository
) : PermissionService {
    override fun findByName(name: String) = repo.findByName(name)
    override fun create(name: String): Permission {

    }
}