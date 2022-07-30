package ru.bgpu.journalserver.controllers;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.bgpu.journalserver.dto.ClassItemDto
import ru.bgpu.journalserver.dto.GradeDto
import ru.bgpu.journalserver.dto.PageDto
import ru.bgpu.journalserver.dto.SubjectDto
import ru.bgpu.journalserver.services.ClassItemService
import ru.bgpu.journalserver.services.GradeService
import ru.bgpu.journalserver.services.SubjectService
import java.time.LocalDate
import java.util.Date

@RestController
class JournalController {
    @Autowired
    lateinit var subjectService: SubjectService
    @Autowired
    lateinit var classItemService: ClassItemService
    @Autowired
    lateinit var gradeService: GradeService

    @GetMapping("/subjects")
    fun getSubjects(@RequestParam(name = "classId") id: Long): List<SubjectDto> =
        subjectService.getSubjectsByClassId(id).map { it.toDto() }

    @GetMapping("/classes")
    fun getClasses(): List<ClassItemDto> = classItemService.getAllClasses().map { it.toDto() }

    @GetMapping("/grades/dates")
    fun getDates(): List<Date> = gradeService.getAllDates()

    @GetMapping("/grades/month")
    fun getMonth(@RequestParam(name = "year") year: Int): List<Int> = gradeService.getAllMonth()

    @GetMapping("/grades/years")
    fun getYears() = gradeService.getAllYears()

    @GetMapping("/students/grades")
    fun getStudentsAndGrades(
        @RequestParam(name = "classId") classId: Long,
        @RequestParam(name = "subjectId") subId: Long,
        @RequestParam(name = "period") period: String
    ) = gradeService.getGradesByClassAndSubjectAndPeriod(classId, subId, period)
}
