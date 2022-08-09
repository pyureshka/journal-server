package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.bgpu.journalserver.dto.ClassItemDto
import ru.bgpu.journalserver.services.ClassItemService
import ru.bgpu.journalserver.services.SubjectService

@RestController
@RequestMapping("/classes")
class ClassItemController {
    @Autowired lateinit var classItemService: ClassItemService
    @Autowired lateinit var subjectService: SubjectService

    @GetMapping
    fun getClasses(): List<ClassItemDto> = classItemService.getAllClasses().map { it.toDto() }

    @PostMapping("/add")
    fun createClass(@RequestBody classItemDto: ClassItemDto) {
        val classItem = classItemDto.toClassItem()
        classItem.subjects = subjectService.listSubjectsByListId(classItemDto.subjectsId)
        classItemService.createClass(classItem)
    }
}