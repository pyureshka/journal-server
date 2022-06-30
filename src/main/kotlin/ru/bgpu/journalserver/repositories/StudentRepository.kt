package ru.bgpu.journalserver.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.bgpu.journalserver.models.ClassItem
import ru.bgpu.journalserver.models.Student

@Repository
interface StudentRepository : CrudRepository<Student, Long> {

    fun findStudentByClassItem(classItem: ClassItem): List<Student>
}