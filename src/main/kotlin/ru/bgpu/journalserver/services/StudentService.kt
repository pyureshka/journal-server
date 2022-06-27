package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.models.Student
import ru.bgpu.journalserver.repositories.StudentRepository


@Service
class StudentService {
    @Autowired
    lateinit var studentRepository: StudentRepository
    fun save(student: Student) = studentRepository.save(student)
}