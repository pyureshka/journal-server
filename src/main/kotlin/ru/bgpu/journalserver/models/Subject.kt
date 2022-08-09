package ru.bgpu.journalserver.models

import ru.bgpu.journalserver.dto.SubjectDto
import javax.persistence.*

@Entity
data class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String? = null,
    @ManyToMany(mappedBy = "subjects", cascade = [CascadeType.ALL])
    var classItems: List<ClassItem> = ArrayList()
) {
    fun toDto(): SubjectDto = SubjectDto(
        id = id,
        title = title,
        classItemsId = classItems.map { it.id }
    )
}
