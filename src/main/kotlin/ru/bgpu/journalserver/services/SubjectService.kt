package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.exeptions.BadRequestException
import ru.bgpu.journalserver.exeptions.ResourceNotFoundException
import ru.bgpu.journalserver.models.Subject
import ru.bgpu.journalserver.repositories.ClassItemRepository
import ru.bgpu.journalserver.repositories.GradeRepository
import ru.bgpu.journalserver.repositories.SubjectRepository

@Service
class SubjectService {
    @Autowired lateinit var subjectRepository: SubjectRepository
    @Autowired lateinit var classItemService: ClassItemService
    @Autowired lateinit var gradeRepository: GradeRepository
    @Autowired lateinit var classItemRepository: ClassItemRepository

    fun get(id: Long): Subject = subjectRepository.findById(id).orElseThrow{ ResourceNotFoundException("Предмет не найден") }
    fun save(subject: Subject) = subjectRepository.save(subject)
    fun getAllSubjects(): List<Subject> = subjectRepository.findAllSubject()
    fun getSubjectsByClassId(id: Long) = subjectRepository.findSubjectByClassItems(classItemService.getById(id))
    fun createSubject(subject: Subject) = subjectRepository.save(subject)
    fun listSubjectsByListId(subjectsId: List<Long?>): List<Subject> =
        if (subjectsId.isEmpty()) {
            listOf()
        } else {
            subjectRepository.findAllSubjectsByListId(subjectsId)
        }
    fun updateSubject(subject: Subject): Subject = save(get(subject.id ?: throw BadRequestException(""))
        .copy(title = subject.title))
}