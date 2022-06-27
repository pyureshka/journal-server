package ru.bgpu.journalserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JournalServerApplication

fun main(args: Array<String>) {
    runApplication<JournalServerApplication>(*args)
}
