package org.keirobm.samples.springangularbackend.app

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class WebMvcConfig : WebMvcConfigurerAdapter() {

    @Bean
    fun passwordEncoder()
        = BCryptPasswordEncoder()

}