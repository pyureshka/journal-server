package ru.bgpu.journalserver.models

import javax.persistence.*

@Entity
data class Grade (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var grade: Float? = null,
    var date: Int? = null,
    @ManyToOne (fetch = FetchType.LAZY)
    var student: Student? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var subject: Subject? = null
)