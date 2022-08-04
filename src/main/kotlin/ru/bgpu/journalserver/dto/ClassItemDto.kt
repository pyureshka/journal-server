package ru.bgpu.journalserver.dto

import ru.bgpu.journalserver.models.ClassItem

data class ClassItemDto(
    var id: Long? = null,
    var liter: String? = null,
    var number: Int? = null,
    var subjects: List <SubjectDto>
) {
    fun toClassItem() = ClassItem(
        id = id,
        liter = liter,
        number = number,
        subjects = subjects.map { it.toSubject() }
    )
}
