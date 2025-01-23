package demo.microkernel.loggingservice.controller

import demo.microkernel.loggingservice.model.LogDto
import demo.microkernel.loggingservice.service.LoggingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/log")
class LoggingController(
    private val loggingService: LoggingService
) {

    @PostMapping
    fun logMessage(@RequestBody logDto: LogDto): ResponseEntity<Void> {
        loggingService.logMessage(logDto.serviceName, logDto.message)
        return ResponseEntity.ok().build()
    }
}