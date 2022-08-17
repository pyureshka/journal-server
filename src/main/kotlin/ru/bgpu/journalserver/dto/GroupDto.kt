package ru.bgpu.journalserver.dto

import ru.bgpu.journalserver.models.Group

data class GroupDto(
    var id: Long? = null,
    var name: String? = null,
) {
    fun toGroup() = Group(
        id = id,
        name = name
    )
}