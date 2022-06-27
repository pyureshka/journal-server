package ru.bgpu.journalserver.models

import javax.persistence.*

@Entity
data class ClassItem (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var liter: String? = null,
    var number: Int? = null,
    @ManyToMany(fetch = FetchType.LAZY)
    var subjects: List<Subject> = ArrayList()
)