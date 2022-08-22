package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.exeptions.ResourceNotFoundException
import ru.bgpu.journalserver.models.Grade
import ru.bgpu.journalserver.repositories.GradeRepository

@Service
class GradeService {
    @Autowired
    lateinit var gradeRepository: GradeRepository

    fun save(grade: Grade) = gradeRepository.save(grade)
    fun getById(id: Long): Grade =
        gradeRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Оценки по ID $id не найдены")

    fun updateGrade(id: Long, newGrade: Grade): Grade =
        save(getById(id).copy(grade = newGrade.grade))

    fun deleteGrade(id: Long) {
        gradeRepository.delete(getById(id))
    }

    fun createGrade(newGrade: Grade) = gradeRepository.save(newGrade)

    fun findAllBySubjectAndClassAndDate(classId: Long, year: Int, month: Int, subjectId: Long) =
        gradeRepository.findAllBySubjectAndClassAndDate(classId, year, month, subjectId)

}