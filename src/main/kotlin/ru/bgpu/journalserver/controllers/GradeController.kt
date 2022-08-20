package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.bgpu.journalserver.dto.GradeDto
import ru.bgpu.journalserver.dto.journal.JournalStudentDto
import ru.bgpu.journalserver.services.GradeService
import ru.bgpu.journalserver.services.StudentService
import ru.bgpu.journalserver.services.SubjectService

@RestController
@RequestMapping("/grades")
@PreAuthorize("hasAnyAuthority('GROUP_ADMIN', 'GROUP_MANAGER')")
class GradeController {
    @Autowired
    lateinit var gradeService: GradeService
    @Autowired
    lateinit var studentService: StudentService
    @Autowired
    lateinit var subjectService: SubjectService

    @GetMapping
    fun getStudentsAndGradesShort(
        @RequestParam(name = "classId") classId: Long,
        @RequestParam(name = "subjectId") subId: Long,
        @RequestParam(name = "period") period: String
    ): List<JournalStudentDto> {
        val parts: List<String> = period.split("-")
        return gradeService.findAllBySubjectAndClassAndDate(classId, parts[0].toInt(), parts[1].toInt(), subId).map {
            JournalStudentDto(it)
        }
    }

    @PutMapping("/{id}")
    fun updateGrade(@PathVariable id: Long, @RequestBody gradeDto: GradeDto) =
        gradeService.updateGrade(id, gradeDto.toGrade()).toDto()

    @DeleteMapping("/{id}")
    fun deleteGrade(@PathVariable id: Long) = gradeService.deleteGrade(id)

    @PostMapping("/add")
    fun createGrade(@RequestBody gradeDto: GradeDto): GradeDto {
        val grade = gradeDto.toGrade()
        grade.student = studentService.get(gradeDto.studentId!!)
        grade.subject = subjectService.get(gradeDto.subject!!.id!!)
        return gradeService.createGrade(grade).toDto()
    }
}
