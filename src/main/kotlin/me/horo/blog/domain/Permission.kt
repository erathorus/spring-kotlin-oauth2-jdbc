package me.horo.blog.domain

import me.horo.blog.annotation.NoArg
import javax.persistence.*

@Entity
@NoArg
data class Permission(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(nullable = false, unique = true)
        val name: String,

        @ManyToMany(mappedBy = "permissions")
        val roles: Set<Role> = HashSet()
)