package ru.bgpu.journalserver.dto

import ru.bgpu.journalserver.models.ClassItem
import ru.bgpu.journalserver.models.Subject


data class ClassItemDto(
    var id: Long? = null,
    var liter: String? = null,
    var number: Int? = null,
) {
    fun toClassItem() = ClassItem(
        id = id,
        liter = liter,
        number = number,
    )
}
