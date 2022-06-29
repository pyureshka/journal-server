package ru.bgpu.journalserver.models

import java.util.Date
import javax.persistence.*

@Entity
data class Grade (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var grade: Int? = null,
    @Temporal(TemporalType.DATE)
    var date: Date? = null, //?
    @ManyToOne (fetch = FetchType.LAZY)
    var student: Student? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var subject: Subject? = null
)