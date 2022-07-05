package ru.bgpu.journalserver.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.bgpu.journalserver.models.Grade
import ru.bgpu.journalserver.models.Student

@Repository
interface GradeRepository : CrudRepository<Grade, Long> {
    fun deleteAllByStudent(student: Student)
}