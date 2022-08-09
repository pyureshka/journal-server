package ru.bgpu.journalserver.dto

import ru.bgpu.journalserver.models.ClassItem

data class ClassItemDto(
    var id: Long? = null,
    var liter: String? = null,
    var number: Int? = null,
    var subjectsId: List<Long?> = ArrayList()
) {
    fun toClassItem() = ClassItem(
        id = id,
        liter = liter,
        number = number
    )
}
