package me.horo.milkyway.service

import me.horo.milkyway.domain.Role
import me.horo.milkyway.repository.RoleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RoleServiceImpl(
        private val repo: RoleRepository
): RoleService {
    override fun findByName(name: String) = repo.findByName(name)
}