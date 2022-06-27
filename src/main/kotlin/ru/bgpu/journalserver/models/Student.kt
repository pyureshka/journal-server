package ru.bgpu.journalserver.models

import javax.persistence.*

@Entity
data class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var first_name: String? = null,
    var last_name: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var class_id: Class
) {}