package ru.bgpu.journalserver.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.bgpu.journalserver.models.Subject

@Repository
interface SubjectRepository : CrudRepository<Subject, Long> {
}