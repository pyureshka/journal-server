package ru.bgpu.journalserver.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import ru.bgpu.journalserver.exeptions.ResourceNotFoundException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RESTAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var securityService: SecurityService

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication?) {
        log.info("success login")
        response.status = HttpServletResponse.SC_OK
        response.contentType = "application/json;charset=UTF8"

        val mapper = ObjectMapper()

        response.writer?.print(mapper.writeValueAsString(
            authentication?.name.toString().let {
                securityService.findStudentByLogin(it)?.toDto() ?:
                throw ResourceNotFoundException("Ошибка получения данных пользователя")
            }
        ))

        response.writer?.flush()
        clearAuthenticationAttributes(request)
    }
}