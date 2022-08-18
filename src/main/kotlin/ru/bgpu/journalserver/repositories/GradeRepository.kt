package ru.bgpu.journalserver.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.bgpu.journalserver.models.Grade
import ru.bgpu.journalserver.models.Student

@Repository
interface GradeRepository : CrudRepository<Grade, Long> {
    fun deleteAllByStudent(student: Student)

    interface JournalData {
        var id: Long?
        var firstName: String?
        var lastName: String?
        var grades: String?
    }

    @Query(
        value = """
            select s.id, s.first_name as firstName, s.last_name as lastName,
                case when sum(coalesce(g.id, 0)) = 0 then '[]' else 
                cast(json_agg(json_build_object('id', g.id, 'date', g.date, 'grade', g.grade)) as text) end as grades
            from student s
                left join grade g on s.id = g.student_id and extract(year from g.date) = ?2 and extract(month from g.date) = ?3
                    where s.class_item_id = ?1
            group by s.id;
        """, nativeQuery = true
    )
    fun findAllBySubjectAndClassAndDate(classId: Long, year: Int, month: Int, subjectId: Long): List<JournalData>
}