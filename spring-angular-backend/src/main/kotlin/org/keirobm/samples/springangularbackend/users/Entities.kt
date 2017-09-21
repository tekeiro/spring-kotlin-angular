package org.keirobm.samples.springangularbackend.users

import org.keirobm.samples.springangularbackend.users.dto.RoleDto
import org.keirobm.samples.springangularbackend.users.dto.UserDto
import javax.persistence.*


/**
 * User entity
 */
@Entity
@Table(name="users")
internal data class User (
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
            var id: Long?,
        var username: String,
        var password: String,
        var firstName: String,
        var lastName: String,
        var active: Boolean
) {

    constructor() : this(null, "", "", "", "", false)

    @ManyToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(
            name="user_roles",
            joinColumns = arrayOf(JoinColumn(name="user_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name="role_id"))
    )
    private var mRoles: MutableSet<Role> = hashSetOf()


    val roles: Set<Role>
        get() = mRoles.toSet()


    internal fun addRole(role: Role) {
        mRoles.add(role)
    }

    internal fun removeRole(role: Role) {
        mRoles.remove(role)
    }


    fun toDto(): UserDto
        = UserDto(id ?: 0L, username, password,
            firstName, lastName, active,
            roles.map { r -> r.toDto() }.toList())

}


/**
 * Role entity
 */
@Entity
@Table(name="roles")
internal data class Role (
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
            var id: Long?,
        var role: String
)
{

    constructor() : this(null, "")

    fun toDto(): RoleDto
        = RoleDto(id ?: 0L, role)

}