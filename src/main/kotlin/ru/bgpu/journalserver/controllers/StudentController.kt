package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.bgpu.journalserver.dto.StudentDto
import ru.bgpu.journalserver.services.StudentService

class StudentController {
    @Autowired lateinit var studentService: StudentService

    @GetMapping("/students")
    fun getClassStudent(@RequestParam(name = "classId") id: Long): List<StudentDto> = studentService.getStudentByClass(id).map { it.toDto() }
    @DeleteMapping("/students/{id}")
    fun delete(@PathVariable id: Long) = studentService.deleteById(id)
    @PostMapping("/students/add")
    fun addStudent(@RequestBody studentDto: StudentDto) = studentService.createStudent(studentDto.toStudent())
    @PutMapping("/students/{id}") //?
    fun updateStudent(@PathVariable id: Long, @RequestBody studentDto: StudentDto) = studentService.updateStudent(id, studentDto.toStudent())
}
