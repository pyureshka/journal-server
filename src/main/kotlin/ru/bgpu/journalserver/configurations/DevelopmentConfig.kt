package ru.bgpu.journalserver.configurations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.bgpu.journalserver.models.ClassItem
import ru.bgpu.journalserver.models.Grade
import ru.bgpu.journalserver.models.Student
import ru.bgpu.journalserver.models.Subject
import ru.bgpu.journalserver.services.ClassItemService
import ru.bgpu.journalserver.services.GradeService
import ru.bgpu.journalserver.services.StudentService
import ru.bgpu.journalserver.services.SubjectService
import java.util.Random

@Configuration
class DevelopmentConfig {
    val listSurnames: List<String> = mutableListOf(
        "Иванов", "Петров", "Сидоров", "Суляндзига","Супрягин","Куцих","Бобров","Атас","Гунидзэ",
        "Запов", "Зайце", "Дятлов", "Егоров", "Дудиков", "Акунин", "Боров", "Подосинников",
        "Тарасов", "Тополев", "Гетман"
    )
    val listNames: List<String> = mutableListOf(
        "Иван", "Петр", "Артём", "Антон","Виталий", "Егор", "Владимир", "Геннадий", "Юрий", "Евгений",
        "Кирилл", "Аккакий", "Владислав", "Владелен"
    )
    val listSubject: List<String> = mutableListOf(
        "Алгебра","Физика", "Математика","Окружающий мир", "Литература", "Русский язык"
    )
    val listLiters: List<String> = mutableListOf(
        "А","Б","В","Г"
    )

    @Autowired
    lateinit var classItemService: ClassItemService
    @Autowired
    lateinit var gradeService: GradeService
    @Autowired
    lateinit var subjectService: SubjectService
    @Autowired
    lateinit var studentService: StudentService

    @Bean
    fun init() = CommandLineRunner {
        val random = Random()
        val listObjSubject: MutableList<Subject> = mutableListOf()
        val listObjStudent: MutableList<Student> = mutableListOf()
        val listObjClass: MutableList<ClassItem> = mutableListOf()
        val listObjGrade: MutableList<Grade> = mutableListOf()

        listSubject.forEach{
            listObjSubject.add(subjectService.save(Subject(title = it)))
        }

        for (i in 0..4) {
            listObjClass.add(i, classItemService.save( ClassItem (
                    liter = listLiters[random.nextInt(listLiters.size)],
                    number = 1+random.nextInt(10),
                    subjects = listObjSubject.fold(ArrayList()) { list, s ->
                        if (random.nextInt(100) < 80) {
                            list.add(s)
                        }
                        list
                    }
            )
            ))
            for (j in 0..10) {
                listObjStudent.add( studentService.save(Student (
                        firstName = listNames[random.nextInt(listNames.size)],
                        lastName = listSurnames[random.nextInt(listSurnames.size)],
                        classItem = listObjClass[i]
                    )
                ))
            }
        }

        listObjStudent.forEach {
            for (i in 0 until it.classItem!!.subjects.size) {
                for (j in 0..random.nextInt(5)) {
                    listObjGrade.add(gradeService.save(Grade (
                            grade = random.nextInt(4)+1,
                            student = it, subject = it.classItem!!.subjects[i]
                        )
                    ))
                }
            }
        }
    }
}