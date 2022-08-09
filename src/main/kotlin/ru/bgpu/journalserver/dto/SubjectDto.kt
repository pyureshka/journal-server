package ru.bgpu.journalserver.dto

import ru.bgpu.journalserver.models.Subject

data class SubjectDto(
    var id: Long? = null,
    var title: String? = null,
    var classItemsId: List<Long?> = ArrayList()
) {
    fun toSubject() = Subject(
        id = id,
        title = title
    )
}
