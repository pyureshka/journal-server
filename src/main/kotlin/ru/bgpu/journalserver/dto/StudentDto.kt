package ru.bgpu.journalserver.dto

import ru.bgpu.journalserver.models.Group
import ru.bgpu.journalserver.models.Student

data class StudentDto(
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var classItem: ClassItemDto? = null,
    var archive: Boolean? = false,
    var groups: MutableList<GroupDto>? = ArrayList()
) {

    fun toStudent() = Student(
        id = id,
        firstName = firstName,
        lastName = lastName,
        classItem = classItem?.toClassItem(),
        archive = archive,
        groups = groups!!.map { it.toGroup() } as MutableList<Group>
    )
}
