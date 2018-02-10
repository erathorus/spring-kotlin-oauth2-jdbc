package me.horo.milkyway.service

import me.horo.milkyway.domain.Role
import me.horo.milkyway.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl(
        private val repo: UserRepository
) : UserDetailsService {
    private val logger = LoggerFactory.getLogger(UserDetailsServiceImpl::class.java)

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repo.findByUsername(username)
        if (!user.isPresent)
            throw UsernameNotFoundException("Username $username is not found")
        return user.get().copy(authorities = getGrantedAuthorities(user.get().roles))
    }

    private fun getGrantedAuthorities(roles: Collection<Role>): MutableCollection<out GrantedAuthority> {
        val permissions = HashSet<String>()
        roles.map { role ->
            permissions.add(role.name)
            role.permissions.mapTo(permissions) { permission -> permission.name }
        }

        val authorities = ArrayList<GrantedAuthority>()
        permissions.mapTo(authorities) { permission ->
            logger.info("Granted permission is $permission")
            SimpleGrantedAuthority(permission)
        }

        return authorities
    }
}