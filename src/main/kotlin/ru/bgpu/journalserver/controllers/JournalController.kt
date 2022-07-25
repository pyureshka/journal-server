package ru.bgpu.journalserver.controllers;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.bgpu.journalserver.dto.ClassItemDto
import ru.bgpu.journalserver.dto.GradeDto
import ru.bgpu.journalserver.dto.SubjectDto
import ru.bgpu.journalserver.services.ClassItemService
import ru.bgpu.journalserver.services.GradeService
import ru.bgpu.journalserver.services.SubjectService

@RestController
class JournalController {
    @Autowired lateinit var subjectService: SubjectService
    @Autowired lateinit var classItemService: ClassItemService
    @Autowired lateinit var gradeService: GradeService

    @GetMapping("/subjects")
    fun getSubjects(@RequestParam(name = "classId") id: Long): List<SubjectDto> =
        subjectService.getSubjectsByClassId(id).map { it.toDto() }
    @GetMapping("/classes")
    fun getClasses(): List<ClassItemDto> = classItemService.getAllClasses().map { it.toDto() }
    @GetMapping("/grades")
    fun getGrades(@RequestParam(name = "subjectId") subId: Long,
                  @RequestParam(name = "studentId") studId: Long) : List<GradeDto> =
        gradeService.getGradesBySubjectIdAndStudentId(subId, studId).map { it.toDto() }
}
