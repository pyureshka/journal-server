package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.bgpu.journalserver.dto.ClassItemDto
import ru.bgpu.journalserver.services.ClassItemService
import ru.bgpu.journalserver.services.SubjectService

@RestController
@RequestMapping("/classes")
@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
class ClassItemController {
    @Autowired lateinit var classItemService: ClassItemService
    @Autowired lateinit var subjectService: SubjectService

    @GetMapping
    fun getClasses(): List<ClassItemDto> = classItemService.getAllClasses().map { it.toDto() }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    fun createClass(@RequestBody classItemDto: ClassItemDto) {
        val classItem = classItemDto.toClassItem()
        classItem.subjects = subjectService.listSubjectsByListId(classItemDto.subjectsId)
        classItemService.createClass(classItem)
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    fun editClassItem(@PathVariable id: Long, @RequestBody classItemDto: ClassItemDto) {
        val classItem = classItemDto.toClassItem()
        classItem.subjects = subjectService.listSubjectsByListId(classItemDto.subjectsId)
        classItemService.updateClassItem(classItem)
    }
}