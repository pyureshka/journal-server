package ru.bgpu.journalserver.dto

import ru.bgpu.journalserver.models.Student

data class StudentDto(
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var classItem: ClassItemDto? = null
) {

    fun toStudent() = Student(
        id = id,
        firstName = firstName,
        lastName = lastName,
        classItem = classItem?.toClassItem()
    )
}
