package ru.bgpu.journalserver.configurations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.bgpu.journalserver.models.*
import ru.bgpu.journalserver.services.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList

@Configuration
class DevelopmentConfig {
    val listSurnames: List<String> = mutableListOf(
        "Иванов", "Петров", "Сидоров", "Суляндзига", "Супрягин", "Куцих", "Бобров", "Атас", "Гунидзэ",
        "Запов", "Зайце", "Дятлов", "Егоров", "Дудиков", "Акунин", "Боров", "Подосинников",
        "Тарасов", "Тополев", "Гетман"
    )
    val listNames: List<String> = mutableListOf(
        "Иван", "Петр", "Артём", "Антон", "Виталий", "Егор", "Владимир", "Геннадий", "Юрий", "Евгений",
        "Кирилл", "Аккакий", "Владислав", "Владелен"
    )
    val listSubject: List<String> = mutableListOf(
        "Алгебра", "Физика", "Математика", "Окружающий мир", "Литература", "Русский язык"
    )
    val listLiters: List<String> = mutableListOf(
        "А", "Б", "В", "Г"
    )

    @Autowired
    lateinit var classItemService: ClassItemService

    @Autowired
    lateinit var gradeService: GradeService

    @Autowired
    lateinit var subjectService: SubjectService

    @Autowired
    lateinit var studentService: StudentService

    @Autowired
    lateinit var groupService: GroupService

    @Bean
    fun init() = CommandLineRunner {
        val random = Random()
        val listObjSubject: MutableList<Subject> = mutableListOf()
        val listObjStudent: MutableList<Student> = mutableListOf()
        val listObjClass: MutableList<ClassItem> = mutableListOf()
        val listObjGrade: MutableList<Grade> = mutableListOf()
        val localDate: LocalDate = LocalDate.now()

        groupService.initDefaultGroups()
        val admin = studentService.save(
            Student(
                firstName = "админ", lastName = "админ", login = "admin", password = "admin",
                groups = mutableListOf(groupService.getByName("GROUP_ADMIN"))
            )
        )

        val manager = studentService.save(
            Student(
                firstName = "мен", lastName = "мен", login = "man", password = "man",
                groups = mutableListOf(groupService.getByName("GROUP_MANAGER"))
            )
        )

        listSubject.forEach {
            listObjSubject.add(subjectService.save(Subject(title = it)))
        }

        for (i in 0..4) {
            listObjClass.add(i, classItemService.save(ClassItem(
                liter = listLiters[random.nextInt(listLiters.size)],
                number = 1 + random.nextInt(10),
                subjects = listObjSubject.fold(ArrayList()) { list, s ->
                    if (random.nextInt(100) < 80) {
                        list.add(s)
                    }
                    list
                }
            )
            ))
            for (j in 0..15) {
                listObjStudent.add(
                    studentService.save(
                        Student(
                            firstName = listNames[random.nextInt(listNames.size)],
                            lastName = listSurnames[random.nextInt(listSurnames.size)],
                            classItem = listObjClass[i],
                            groups = mutableListOf(groupService.getByName("GROUP_STUDENTS"))
                        )
                    )
                )
            }
        }

        listObjStudent.forEach {
            for (i in 0 until it.classItem!!.subjects.size) {
                for (j in 0..random.nextInt(40)) {
                    listObjGrade.add(
                        gradeService.save(
                            Grade(
                                grade = random.nextInt(5) + 1,
                                student = it,
                                subject = it.classItem!!.subjects[i],
                                date = Date.from(
                                    localDate.minusDays(random.nextInt(365).toLong())
                                        .atStartOfDay(ZoneId.systemDefault()).toInstant()
                                )
                            )
                        )
                    )
                }
            }
        }
    }
}