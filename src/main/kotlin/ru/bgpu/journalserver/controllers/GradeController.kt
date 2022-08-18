package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.bgpu.journalserver.dto.GradeDto
import ru.bgpu.journalserver.dto.journal.JournalStudentDto
import ru.bgpu.journalserver.services.GradeService

@RestController
@RequestMapping("/grades")
class GradeController {
    @Autowired
    lateinit var gradeService: GradeService

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
    fun createGrade(@RequestBody gradeDto: GradeDto) = gradeService.createGrade(gradeDto.toGrade())
}
