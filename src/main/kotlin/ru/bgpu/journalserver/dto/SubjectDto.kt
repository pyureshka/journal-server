package ru.bgpu.journalserver.dto

import ru.bgpu.journalserver.models.ClassItem
import ru.bgpu.journalserver.models.Subject

data class SubjectDto(
    var id: Long? = null,
    var title: String? = null
) {
    fun toSubject() = Subject(
        id = id,
        title = title
    )
}
