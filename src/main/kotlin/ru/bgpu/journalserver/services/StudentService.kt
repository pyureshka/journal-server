package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.models.Student
import ru.bgpu.journalserver.repositories.StudentRepository


@Service
class StudentService {
    @Autowired lateinit var studentRepository: StudentRepository
    @Autowired lateinit var classItemService: ClassItemService

    fun save(student: Student) = studentRepository.save(student)
    fun getStudentByClass(id: Long) = studentRepository.findStudentByClassItem(classItemService.getById(id))
    fun deleteById(studentId: Long) = studentRepository.deleteById(studentId)
    fun createStudent(student: Student){
        studentRepository.save(student)
    }
    fun updateStudent(id: Long, newStudent: Student): Student {
        val student: Student = studentRepository.findById(id) as Student
        student.firstName = newStudent.firstName
        student.lastName = newStudent.lastName
        student.classItem = newStudent.classItem
        return save(student)
    }
}