package ru.bgpu.journalserver.models

import ru.bgpu.journalserver.dto.ClassItemDto
import javax.persistence.*

@Entity
data class ClassItem (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var liter: String? = null,
    var number: Int? = null,
    @ManyToMany(fetch = FetchType.LAZY)
    var subjects: List<Subject> = ArrayList()
) {
    fun toDto(): ClassItemDto = ClassItemDto (
        id = id,
        liter = liter,
        number = number,
        subjects = subjects.map { it.toDto() }
    )
}