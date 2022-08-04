package ru.bgpu.journalserver.models

import ru.bgpu.journalserver.dto.GradeDto
import java.util.Date
import javax.persistence.*

@Entity
data class Grade (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var grade: Int? = null,
    @Temporal(TemporalType.DATE)
    var date: Date? = null,
    @ManyToOne (fetch = FetchType.LAZY)
    var student: Student,
    @ManyToOne(fetch = FetchType.LAZY)
    var subject: Subject
) {
    fun toDto(): GradeDto = GradeDto(
        id = id,
        grade = grade,
        date = date,
        student = student.toDto(),
        subject = subject.toDto()
    )
}