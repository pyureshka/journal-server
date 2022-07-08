package ru.bgpu.journalserver.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException


@Service
class UserDetailsServiceImpl : UserDetailsService {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Autowired lateinit var securityService: SecurityService

    override fun loadUserByUsername(login: String?): UserDetails {
        log.info("auth $login")
        if(login!=null) {
            val student = securityService.findStudentByLogin(login)
            if (student != null) {
                log.info("user: ${student.login} - ${student.firstName} ${student.lastName}")
                return org.springframework.security.core.userdetails.User(
                    student.login,
                    student.password,
                    student.active ?: true,
                    true, true, true,
                    emptyList()
                )
            }
        }
        throw UsernameNotFoundException("could not find the user '$login'")
    }
}