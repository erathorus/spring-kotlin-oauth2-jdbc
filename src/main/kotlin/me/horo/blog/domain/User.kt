package me.horo.blog.domain

import me.horo.blog.annotation.NoArg
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@NoArg
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(nullable = false, unique = true)
        private var username: String,

        @Column(nullable = false)
        private var password: String,

        @ManyToMany
        @JoinTable(
                name = "user_role",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "role_id")]
        )
        private var roles: Set<Role> = HashSet(),

        @Column(name = "non_expired", nullable = false)
        private val nonExpired: Boolean = true,
        @Column(name = "non_locked", nullable = false)
        private val nonLocked: Boolean = true,
        @Column(nullable = false)
        private val enabled: Boolean = true,
        @Column(name = "credentials_non_expired", nullable = false)
        private val credentialsNonExpired: Boolean = true
): UserDetails {
    override fun getUsername() = username
    override fun getPassword() = password
    override fun isAccountNonExpired() = nonExpired
    override fun isAccountNonLocked() = nonLocked
    override fun isEnabled() = enabled
    override fun isCredentialsNonExpired() = credentialsNonExpired
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = HashSet()
}