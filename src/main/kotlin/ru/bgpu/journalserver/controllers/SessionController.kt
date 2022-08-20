package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.bgpu.journalserver.services.StudentService

@RestController
class SessionController {

    @Autowired
    lateinit var studentService: StudentService

    @GetMapping("/current-user")
    fun currentUser() = studentService.sessionStudent().toDto()
}