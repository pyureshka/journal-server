package ru.bgpu.journalserver.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.exeptions.BadRequestException
import ru.bgpu.journalserver.exeptions.ResourceNotFoundException
import ru.bgpu.journalserver.models.ClassItem
import ru.bgpu.journalserver.repositories.ClassItemRepository

@Service
class ClassItemService {
    @Autowired
    lateinit var classItemRepository: ClassItemRepository
    fun get(id: Long): ClassItem = classItemRepository.findById(id)
        .orElseThrow{ ResourceNotFoundException("Предмет не найден") }
    fun save(classItem: ClassItem) = classItemRepository.save(classItem)
    fun getAllClasses(): List<ClassItem> = classItemRepository.findAll() as List<ClassItem>
    fun getById(id: Long): ClassItem =
        classItemRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Класс с ID $id не найден")
    fun createClass(classItem: ClassItem) = classItemRepository.save(classItem)
    fun listClassesByListId(classItemsId: List<Long?>): List<ClassItem> =
        if (classItemsId.isEmpty()) {
            listOf()
        } else {
            classItemRepository.findAllClassItemsByListId(classItemsId)
        }

    fun updateClassItem(classItem: ClassItem) {
        save(get(classItem.id ?: throw BadRequestException(""))
            .copy(liter = classItem.liter, number = classItem.number, subjects = classItem.subjects))
    }

}