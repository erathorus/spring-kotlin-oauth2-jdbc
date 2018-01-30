package me.horo.blog.service

import me.horo.blog.domain.User
import me.horo.blog.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val repo: UserRepository
): UserService {
    private val encoder = BCryptPasswordEncoder()

    override fun create(user: User): User = repo.save(user.copy(password = encoder.encode(user.password)))
}