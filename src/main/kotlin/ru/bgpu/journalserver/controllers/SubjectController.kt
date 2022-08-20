package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.bgpu.journalserver.dto.SubjectDto
import ru.bgpu.journalserver.services.ClassItemService
import ru.bgpu.journalserver.services.SubjectService

@RestController
@RequestMapping("/subjects")
@PreAuthorize("hasAnyAuthority('GROUP_ADMIN', 'GROUP_MANAGER')")
class SubjectController {
    @Autowired
    lateinit var subjectService: SubjectService
    @Autowired
    lateinit var classItemService: ClassItemService

    @GetMapping
    fun getSubjects(@RequestParam(name = "classId") id: Long): List<SubjectDto> =
        subjectService.getSubjectsByClassId(id).map { it.toDto() }

    @GetMapping("/all")
    fun getAllSubjects() = subjectService.getAllSubjects().map { it.toDto() }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('GROUP_ADMIN')")
    fun createSubject(@RequestBody subjectDto: SubjectDto) {
        val subject = subjectDto.toSubject()
        subject.classItems = classItemService.listClassesByListId(subjectDto.classItemsId)
        subjectService.createSubject(subject)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GROUP_ADMIN')")
    fun updateSubject(@PathVariable id: Long, @RequestBody subjectDto: SubjectDto) =
        subjectService.updateSubject(subjectDto.toSubject()).toDto()
}