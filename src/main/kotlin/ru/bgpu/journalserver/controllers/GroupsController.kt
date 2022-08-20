package ru.bgpu.journalserver.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.bgpu.journalserver.dto.GroupDto
import ru.bgpu.journalserver.services.GroupService

@RestController
@RequestMapping("/groups")
@PreAuthorize("hasAnyAuthority('ADMIN')")
class GroupsController {
    @Autowired lateinit var groupService: GroupService

    @GetMapping
    fun getGroupByName(@RequestParam (name="groupName") name: String): GroupDto = groupService.getByName(name).toDto()
}