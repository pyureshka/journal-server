package ru.bgpu.journalserver.exeptions

class BadRequestException: RuntimeException  {
        constructor(message: String? = null) : super(message)
}