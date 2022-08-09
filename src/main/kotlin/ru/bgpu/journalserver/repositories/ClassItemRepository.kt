package ru.bgpu.journalserver.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.bgpu.journalserver.models.ClassItem

@Repository
interface ClassItemRepository : CrudRepository<ClassItem, Long> {
    @Query("select c from ClassItem c where c.id in ?1")
    fun findAllClassItemsByListId(classItemsId: List<Long?>): List<ClassItem>
}