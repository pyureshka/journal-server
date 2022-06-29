package ru.bgpu.journalserver.dto

import ru.bgpu.journalserver.models.Student
import ru.bgpu.journalserver.models.Subject
import java.util.*

data class GradeDto(
    var id: Long? = null,
    var grade: Int? = null,
    var date: Date? = null,
    var student: Student? = null,
    var subject: Subject? = null
)
