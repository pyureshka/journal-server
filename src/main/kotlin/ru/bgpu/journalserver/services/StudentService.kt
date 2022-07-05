package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.exeptions.BadRequestException
import ru.bgpu.journalserver.exeptions.ResourceNotFoundException
import ru.bgpu.journalserver.models.Student
import ru.bgpu.journalserver.repositories.GradeRepository
import ru.bgpu.journalserver.repositories.StudentRepository
import javax.transaction.Transactional


@Service
class StudentService {
    @Autowired lateinit var studentRepository: StudentRepository
    @Autowired lateinit var classItemService: ClassItemService
    @Autowired lateinit var gradeRepository: GradeRepository

    fun get(id: Long): Student = studentRepository.findById(id).orElseThrow{ ResourceNotFoundException("Ученик не найден") }
    fun save(student: Student) = studentRepository.save(student)
    fun getStudentByClass(id: Long) = studentRepository.findStudentByClassItem(classItemService.getById(id))
    @Transactional
    fun deleteStudentById(id: Long) {
        val student = get(id)
        gradeRepository.deleteAllByStudent(student)
        studentRepository.delete(student)
    }
    fun updateStudent(student: Student): Student = save(get(student.id ?: throw BadRequestException("")).copy(firstName = student.firstName, lastName = student.lastName))
    fun createStudent(student: Student): Student = studentRepository.save(student)
}