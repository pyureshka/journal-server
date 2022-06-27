package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.models.ClassItem
import ru.bgpu.journalserver.repositories.ClassItemRepository

@Service
class ClassItemService {
    @Autowired
    lateinit var classItemRepository: ClassItemRepository
    fun save(classItem: ClassItem) = classItemRepository.save(classItem)
}