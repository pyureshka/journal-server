package ru.bgpu.journalserver.models

import javax.persistence.*

@Entity
data class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String? = null,
    @ManyToMany(mappedBy = "subjects")
    var classItems: List<ClassItem> = ArrayList()
)
