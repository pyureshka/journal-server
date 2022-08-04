package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.bgpu.journalserver.dto.GradeDto
import ru.bgpu.journalserver.services.GradeService

@RestController
@RequestMapping("/grades")
class GradeController {
    @Autowired lateinit var gradeService: GradeService

    @GetMapping
    fun getStudentsAndGrades(
        @RequestParam(name = "classId") classId: Long,
        @RequestParam(name = "subjectId") subId: Long,
        @RequestParam(name = "period") period: String
    ) = gradeService.getGradesByClassAndSubjectAndPeriod(classId, subId, period)
    @PutMapping("/{id}")
    fun updateGrade(@PathVariable id: Long, @RequestBody gradeDto: GradeDto) = gradeService.updateGrade(id, gradeDto.toGrade()).toDto()
    @DeleteMapping("/{id}")
    fun deleteGrade(@PathVariable id: Long) = gradeService.deleteGrade(id)
    @PostMapping("/add")
    fun createGrade(@RequestBody gradeDto: GradeDto) = gradeService.createGrade(gradeDto.toGrade())
}
