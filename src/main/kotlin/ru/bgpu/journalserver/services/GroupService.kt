package ru.bgpu.journalserver.services

import ru.bgpu.journalserver.models.Group
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bgpu.journalserver.repositories.GroupRepository

@Service
class GroupService {
    @Autowired lateinit var groupRepository: GroupRepository

    fun save(group: Group) = groupRepository.save(group)
    fun initDefaultGroups() {
        listOf(
            Group(name = "GROUP_ADMIN", description = "Администратор системы"),
            Group(name = "GROUP_STUDENTS",description = "Ученик")
        ).forEach { save(it) }
    }
    fun getByName(name: String): Group = groupRepository.findByName(name)
}