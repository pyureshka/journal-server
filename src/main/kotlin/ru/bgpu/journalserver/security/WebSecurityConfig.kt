package ru.bgpu.journalserver.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable().authorizeRequests()
            .anyRequest().authenticated()
//            .anyRequest().permitAll()
            .and().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
            .and().userDetailsService(userDetailsService())
            .formLogin().loginPage("/auth").permitAll()
            .usernameParameter("login")
            .passwordParameter("pwd")
            .successHandler(restAuthenticationSuccessHandler())
            .failureHandler(SimpleUrlAuthenticationFailureHandler())
            .and().csrf().disable()
            .logout().permitAll().logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
            .and().cors()
    }

    @Bean
    fun restAuthenticationSuccessHandler() : RESTAuthenticationSuccessHandler = RESTAuthenticationSuccessHandler()

    @Bean
    fun restAuthenticationEntryPoint() : RESTAuthenticationEntryPoint = RESTAuthenticationEntryPoint()

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return object : PasswordEncoder {
            override fun encode(charSequence: CharSequence): String {
                return charSequence.toString()
            }

            override fun matches(charSequence: CharSequence, s: String): Boolean {
                return charSequence.toString() == s
            }
        }
    }

    @Bean
    override fun userDetailsService() : UserDetailsServiceImpl = UserDetailsServiceImpl()

}

private fun HttpSecurity.headers(s: String) {

}
