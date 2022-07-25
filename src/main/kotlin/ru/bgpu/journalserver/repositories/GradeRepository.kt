package ru.bgpu.journalserver.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.bgpu.journalserver.models.Grade
import ru.bgpu.journalserver.models.Student
import ru.bgpu.journalserver.models.Subject

@Repository
interface GradeRepository : CrudRepository<Grade, Long> {
    fun deleteAllByStudent(student: Student)
    fun findAllBySubjectAndStudent(subject: Subject, student: Student): List<Grade>
}