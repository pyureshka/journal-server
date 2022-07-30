package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.dto.PageDto
import ru.bgpu.journalserver.exeptions.ResourceNotFoundException
import ru.bgpu.journalserver.models.Grade
import ru.bgpu.journalserver.repositories.GradeRepository
import ru.bgpu.journalserver.repositories.SubjectRepository

@Service
class GradeService {
    @Autowired lateinit var gradeRepository: GradeRepository
    @Autowired lateinit var subjectRepository: SubjectRepository
    @Autowired lateinit var studentService: StudentService
    fun save(grade: Grade) = gradeRepository.save(grade)
    fun getById(id: Long): Grade =
        gradeRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Оценки по ID $id не найдены")

    fun getAllDates() = gradeRepository.findAllDates()

    fun getAllMonth(): List<Int> {
        val listMonth: MutableList<Int> = ArrayList()
        gradeRepository.findAllDates().forEach{ listMonth.add( it.month+1 ) }
        return listMonth.distinct()
    }

    fun getAllYears(): List<Int> {
        val listMonth: MutableList<Int> = ArrayList()
        gradeRepository.findAllDates().forEach{ listMonth.add( it.year+1900 ) }
        return listMonth.distinct()
    }

    fun getGradesByClassAndSubjectAndPeriod(classId: Long, subId: Long, date: String): MutableList<PageDto>? {
        val students = studentService.getStudentByClass(classId)
        val page: MutableList<PageDto> = ArrayList()
        val subject = subjectRepository.findSubjectById(subId)
        val parts: List<String> = date.split("-")

        students.forEach{ student ->
                val item = PageDto()
                item.student = student.toDto()
                item.grades = gradeRepository.findAllBySubjectAndStudentAndDate(subject, student, parts[0].toInt(), parts[1].toInt()).map { it.toDto() }
                page.add(item)
        }
        return page
    }
}