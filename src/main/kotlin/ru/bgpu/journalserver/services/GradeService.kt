package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.models.Grade
import ru.bgpu.journalserver.repositories.GradeRepository


@Service
class GradeService {
    @Autowired
    lateinit var gradeRepository: GradeRepository
    fun save(grade: Grade) = gradeRepository.save(grade)
}