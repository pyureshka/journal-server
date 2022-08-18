package ru.bgpu.journalserver.dto.journal


import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import ru.bgpu.journalserver.repositories.GradeRepository

class JournalStudentDto(data: GradeRepository.JournalData) {
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null
    var grades: List<JournalGradeDto>? = null

    init {
        val mapper = jacksonObjectMapper()
        id = data.id
        firstName = data.firstName
        lastName = data.lastName
        grades = data.grades?.let { mapper.readValue(it) }
    }
}