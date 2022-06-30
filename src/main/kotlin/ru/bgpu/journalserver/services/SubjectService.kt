package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.models.Subject
import ru.bgpu.journalserver.repositories.SubjectRepository


@Service
class SubjectService {
    @Autowired
    lateinit var subjectRepository: SubjectRepository
    fun save(subject: Subject) = subjectRepository.save(subject)
    fun getAllSubjects(): List<Subject> = subjectRepository.findAll() as List<Subject>
}