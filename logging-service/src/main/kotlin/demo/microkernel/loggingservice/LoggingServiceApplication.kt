package demo.microkernel.loggingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoggingServiceApplication

fun main(args: Array<String>) {
    runApplication<LoggingServiceApplication>(*args)
}
