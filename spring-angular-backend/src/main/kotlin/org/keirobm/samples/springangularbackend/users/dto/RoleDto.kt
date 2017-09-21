package org.keirobm.samples.springangularbackend.users.dto

import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.NotNull

data class RoleDto (
        val id: Long,
        @NotNull @NotBlank
            val role: String
)
{

    constructor() : this(0L, "")

}