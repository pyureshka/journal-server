package ru.bgpu.journalserver.dto

data class PageDto (
    var student: StudentDto? = null,
    var grades: List<GradeDto> ? = null
)
