package me.horo.milkyway.service

import me.horo.milkyway.domain.User
import me.horo.milkyway.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserServiceImpl(
        private val repo: UserRepository,
        private val encoder: PasswordEncoder
) : UserService {
    override fun create(user: User): User = repo.save(user.copy(password = encoder.encode(user.password)))
    override fun findByUsername(username: String): User = repo.findByUsername(username)
}