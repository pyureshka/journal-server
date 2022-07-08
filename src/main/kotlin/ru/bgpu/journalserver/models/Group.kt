package ru.bgpu.journalserver.models

import javax.persistence.*

@Entity
@Table(name="groups")
data class Group (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long ?= null,
    var name: String? = null,
    var shortName: String? = null,

    var description: String? = null,
    var type: String? = null,

    @ManyToMany(mappedBy = "groups",cascade = [CascadeType.ALL])
    var students: List<Student> = ArrayList()
)