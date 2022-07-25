package ru.bgpu.journalserver.models

import ru.bgpu.journalserver.dto.GradeDto
import ru.bgpu.journalserver.dto.SubjectDto
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
    var student: Student? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var subject: Subject? = null
) {
    fun toDto(): GradeDto = GradeDto(
        id = id,
        grade = grade,
        date = date
    )
}