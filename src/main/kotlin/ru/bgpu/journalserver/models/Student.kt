package ru.bgpu.journalserver.models

import javax.persistence.*

@Entity
data class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var classItem: ClassItem? = null
) {}