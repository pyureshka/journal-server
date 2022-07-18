package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.bgpu.journalserver.dto.StudentDto
import ru.bgpu.journalserver.services.StudentService

@RestController
@RequestMapping("/students")
class StudentController {
    @Autowired lateinit var studentService: StudentService

    @GetMapping
    fun getStudentsClass(@RequestParam(name = "classId") id: Long): List<StudentDto> = studentService.getStudentByClass(id).map { it.toDto() }
    @GetMapping("/{id}")
    fun getStudent(@PathVariable id: Long): StudentDto = studentService.get(id).toDto()
    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long) { studentService.deleteStudentById(id) }
    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id: Long, @RequestBody studentDto: StudentDto) = studentService.updateStudent(studentDto.toStudent()).toDto()
    @PostMapping("/add")
    fun addStudent(@RequestBody studentDto: StudentDto) = studentService.createStudent(studentDto.toStudent()).toDto()
}
