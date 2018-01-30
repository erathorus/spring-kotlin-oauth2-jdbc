package me.horo.blog.domain

import me.horo.blog.annotation.NoArg
import javax.persistence.*

@Entity
@NoArg
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(nullable = false, unique = true)
        val name: String,

        @ManyToMany
        @JoinTable(
                name = "role_permission",
                joinColumns = [JoinColumn(name = "role_id")],
                inverseJoinColumns = [JoinColumn(name = "permission_id")]
        )
        val permissions: Set<Permission> = HashSet(),

        @ManyToMany(mappedBy = "roles")
        val users: Set<User> = HashSet()
)