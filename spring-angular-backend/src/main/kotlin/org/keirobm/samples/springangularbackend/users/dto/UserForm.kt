package org.keirobm.samples.springangularbackend.users.dto

import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserForm(
        @NotNull @NotBlank
            var username: String,
        @NotNull @Size(min=6) @NotBlank
            var password: String,
        @NotNull @NotBlank
            var firstName: String,
        @NotNull @NotBlank
            var lastName: String
)
{

    constructor() : this("", "", "", "")


    fun toDto(): UserDto {
        return UserDto(
                0L, username, password, firstName,
                lastName, true, listOf()
        )
    }
}