package ru.bgpu.journalserver.models

import ru.bgpu.journalserver.dto.GroupDto
import ru.bgpu.journalserver.dto.StudentDto
import javax.persistence.*

@Entity
data class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var classItem: ClassItem? = null,

    var login: String? = null,
    var password: String? = null,
    var archive: Boolean? = false,

    @ManyToMany
    var groups: MutableList<Group> = ArrayList()
) {
    fun toDto(): StudentDto = StudentDto(
        id = id,
        firstName = firstName,
        lastName = lastName,
        classItem = classItem?.toDto(),
        archive = archive,
        groups = groups.map { it.toDto() } as MutableList<GroupDto>
    )
}