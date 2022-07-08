package ru.bgpu.journalserver.repositories

import ru.bgpu.journalserver.models.Group
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository : CrudRepository<Group, Long> {
    fun findByName(name: String) : Group
}