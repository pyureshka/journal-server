package ru.bgpu.journalserver.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.bgpu.journalserver.models.Grade
import ru.bgpu.journalserver.models.Student
import ru.bgpu.journalserver.models.Subject
import java.util.Date

@Repository
interface GradeRepository : CrudRepository<Grade, Long> {
    fun deleteAllByStudent(student: Student)
    @Query("select g from Grade g where g.subject=?1 and g.student=?2 and year(g.date)=?3 and month(g.date)=?4")
    fun findAllBySubjectAndStudentAndDate(subject: Subject, student: Student, year: Int, month: Int): List<Grade>
    @Query("select distinct g.date from Grade g")
    fun findAllDates(): List<Date>
}