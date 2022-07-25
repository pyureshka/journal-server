package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.exeptions.ResourceNotFoundException
import ru.bgpu.journalserver.models.ClassItem
import ru.bgpu.journalserver.models.Grade
import ru.bgpu.journalserver.models.Student
import ru.bgpu.journalserver.models.Subject
import ru.bgpu.journalserver.repositories.GradeRepository


@Service
class GradeService {
    @Autowired
    lateinit var gradeRepository: GradeRepository
    @Autowired lateinit var studentService: StudentService
    @Autowired lateinit var subjectService: SubjectService
    fun save(grade: Grade) = gradeRepository.save(grade)
    fun getById(id: Long): Grade =
        gradeRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Оценки по ID $id не найдены")
    fun getGradesBySubjectIdAndStudentId(subId: Long, studId: Long) =
        gradeRepository.findAllBySubjectAndStudent(subjectService.get(subId),studentService.get(studId))
}