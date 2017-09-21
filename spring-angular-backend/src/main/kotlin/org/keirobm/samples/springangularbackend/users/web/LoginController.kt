package org.keirobm.samples.springangularbackend.users.web

import org.keirobm.samples.springangularbackend.users.UserAndRoleService
import org.keirobm.samples.springangularbackend.users.dto.UserDto
import org.keirobm.samples.springangularbackend.users.dto.UserForm
import org.keirobm.samples.springangularbackend.utils.set
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid


@Controller
@RequestMapping
class LoginController
(
        private val userAndRoleService: UserAndRoleService
) {

    @RequestMapping("/login", method = arrayOf(RequestMethod.GET))
    fun login(model: Model): String {
        return "login"
    }

    @RequestMapping("/register", method = arrayOf(RequestMethod.GET))
    fun registration(model: Model): String {
        model["user"] = UserForm()
        return "registration"
    }


    @RequestMapping("/register", method = arrayOf(RequestMethod.POST))
    fun createUser(
            model: Model,
            @Valid @ModelAttribute("user") user: UserForm,
            result: BindingResult
    ): String {
        if (result.hasErrors()) {
            return "registration"
        }
        else {
            if (userAndRoleService.findUserByUsername(user.username) != null) {
                model["userYaExiste"] = true
                return "registration"
            }
            userAndRoleService.createUser(user.toDto())
            return "redirect:/login"
        }

    }

}