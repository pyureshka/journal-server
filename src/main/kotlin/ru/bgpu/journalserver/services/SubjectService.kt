package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.exeptions.ResourceNotFoundException
import ru.bgpu.journalserver.models.Student
import ru.bgpu.journalserver.models.Subject
import ru.bgpu.journalserver.repositories.SubjectRepository


@Service
class SubjectService {
    @Autowired lateinit var subjectRepository: SubjectRepository
    @Autowired lateinit var classItemService: ClassItemService

    fun get(id: Long): Subject = subjectRepository.findById(id).orElseThrow{ ResourceNotFoundException("Предмет не найден") }
    fun save(subject: Subject) = subjectRepository.save(subject)
    fun getAllSubjects(): List<Subject> = subjectRepository.findAll() as List<Subject>
    fun getSubjectsByClassId(id: Long) = subjectRepository.findSubjectByClassItems(classItemService.getById(id))
}