package ru.bgpu.journalserver.dto.journal

import java.util.*

data class JournalGradeDto(
    var id: Long? = null,
    var grade: Int? = null,
    var date: Date? = null
)
