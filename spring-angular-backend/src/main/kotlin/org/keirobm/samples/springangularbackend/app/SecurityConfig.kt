package org.keirobm.samples.springangularbackend.app

import org.keirobm.samples.springangularbackend.app.beans.MyAuthenticationProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
class SecurityConfig
(
        private val authProvider: MyAuthenticationProvider
)
    : WebSecurityConfigurerAdapter()
{



    override fun configure(http: HttpSecurity?) {
        http?.let { http ->
            http
                    .authorizeRequests()
                        .antMatchers("/").authenticated()
                        .antMatchers("/css/**").permitAll()
                        .antMatchers("/js/**").permitAll()
                        .antMatchers("/ngapp/**").permitAll()
                        .antMatchers("/images/**").permitAll()
                        .antMatchers("/register").permitAll()
                        .antMatchers("/login").permitAll()
                        .antMatchers("/logout").authenticated()
                        .antMatchers("/access-denied").permitAll()
                        .anyRequest().authenticated()
                    .and()
                    .csrf().disable()
                    .formLogin()
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/")
                        .usernameParameter("username")
                        .passwordParameter("password")
                    .and()
                    .logout()
                        .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                    .and()
                    .exceptionHandling()
                        .accessDeniedPage("/access-denied")
                    .and()
                        .authenticationProvider(authProvider)
        }
    }

    override fun configure(web: WebSecurity?) {
        web?.let { web ->
            web
                    .ignoring()
                    .antMatchers(
                            "/js/**", "/css/**",
                            "/images/**", "/ngapp/**")
        }
    }
}