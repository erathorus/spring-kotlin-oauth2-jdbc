package me.horo.milkyway.service

import me.horo.milkyway.domain.Role
import me.horo.milkyway.repository.RoleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class RoleServiceImpl(
        private val repo: RoleRepository
) : RoleService {
    @Transactional
    override fun tryCreate(role: Role): Optional<Role> {
        if (repo.findByName(role.name).isPresent)
            return Optional.empty()
        return Optional.of(repo.save(role))
    }

    @Transactional
    override fun findByName(name: String) = repo.findByName(name)
}