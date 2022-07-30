package ru.bgpu.journalserver.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.bgpu.journalserver.models.ClassItem
import ru.bgpu.journalserver.models.Subject

@Repository
interface SubjectRepository : CrudRepository<Subject, Long> {
    @Query("select distinct s from Subject s join s.classItems c where c=?1")
    fun findSubjectByClassItems(classItem: ClassItem): List<Subject>
    fun findSubjectById (subId: Long): Subject
}