package ru.bgpu.journalserver.models

import ru.bgpu.journalserver.dto.GroupDto
import javax.persistence.*

@Entity
@Table(name="groups")
data class Group (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long ?= null,
    var name: String? = null,

    var description: String? = null,
    var type: String? = null,

    @ManyToMany(mappedBy = "groups",cascade = [CascadeType.ALL])
    var students: List<Student> = ArrayList()
) {
    fun toDto() = GroupDto (
        id = id,
        name = name
    )
}