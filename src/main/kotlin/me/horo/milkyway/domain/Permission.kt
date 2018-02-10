package me.horo.milkyway.domain

import me.horo.milkyway.annotation.NoArg
import java.io.Serializable
import javax.persistence.*

@Entity
@NoArg
data class Permission(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false, unique = true)
        val name: String,

        @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
        val roles: Set<Role> = HashSet()
) : Serializable {
    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other === null || other !is Permission) {
            return false
        }
        if (this::class != other::class) {
            return false
        }
        return name == other.name
    }
}