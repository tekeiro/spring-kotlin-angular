package org.keirobm.samples.springangularbackend.app.beans

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SpringUser

import org.keirobm.samples.springangularbackend.users.UserAndRoleService
import org.springframework.security.authentication.*
import org.springframework.transaction.annotation.Transactional


@Service
class MyAuthenticationProvider
(
        private val userAndRoleService: UserAndRoleService,
        private val passwordEncoder: BCryptPasswordEncoder
)
    : AuthenticationProvider
{



    @Transactional
    override fun authenticate(authentication: Authentication?): Authentication {
        return authentication?.let { auth ->
            val username = auth.principal.toString()
            val password = auth.credentials.toString()

            val user = userAndRoleService.findUserByUsername(username)
            user ?: throw BadCredentialsException("User or password is invalid")
            if (! user.active)
                throw DisabledException("User is not active")
            if (! passwordEncoder.matches(password, user.password))
                throw BadCredentialsException("User or password is invalid")

            val authorities: Collection<GrantedAuthority> =
                    user.roles.map { role -> SimpleGrantedAuthority(role.role) }.toList()
            val customUser = SpringUser(username, password, user.active,
                    true /* accountNonExpired*/, true /* credentials non expired */,
                    true /* not locked */, authorities
                    )
            UsernamePasswordAuthenticationToken(customUser, password, authorities)
        } !!
    }



    override fun supports(authentication: Class<*>?)
        = UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)

}