package ru.bgpu.journalserver.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.models.Student
import ru.bgpu.journalserver.repositories.StudentRepository

@Service
class SecurityService() {

    @Autowired
    lateinit var studentRepository: StudentRepository

    fun findStudentByLogin(login: String): Student? = studentRepository.findStudentByLogin(login)
}