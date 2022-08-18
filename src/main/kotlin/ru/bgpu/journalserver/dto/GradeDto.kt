package ru.bgpu.journalserver.dto

import ru.bgpu.journalserver.models.Grade
import java.util.*

data class GradeDto(
    var id: Long? = null,
    var grade: Int? = null,
    var date: Date? = null,
    var student: StudentDto,
    var subject: SubjectDto
) {
    fun toGrade() = Grade(
        id = id,
        grade = grade,
        date = date,
        student = student.toStudent(),
        subject = subject.toSubject()
    )
}
