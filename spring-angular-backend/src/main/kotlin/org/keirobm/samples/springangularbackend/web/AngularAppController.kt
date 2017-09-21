package org.keirobm.samples.springangularbackend.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping
class AngularAppController {

    @RequestMapping("/", method = arrayOf(RequestMethod.GET))
    fun angularApp(): String
        = "ng-app"

}