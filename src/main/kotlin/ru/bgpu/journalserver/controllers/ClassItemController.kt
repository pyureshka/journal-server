package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.bgpu.journalserver.dto.ClassItemDto
import ru.bgpu.journalserver.services.ClassItemService

@RestController
@RequestMapping("/classes")
class ClassItemController {
    @Autowired lateinit var classItemService: ClassItemService

    @GetMapping
    fun getClasses(): List<ClassItemDto> = classItemService.getAllClasses().map { it.toDto() }

    @PostMapping("/add")
    fun createClass(@RequestBody classItemDto: ClassItemDto) = classItemService.createClass(classItemDto.toClassItem())
}