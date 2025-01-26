package demo.microkernel.loggingservice.controller

import demo.microkernel.loggingservice.model.LogDto
import demo.microkernel.loggingservice.service.LoggingService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class LoggingControllerTest {

    private val loggingService: LoggingService = mock(LoggingService::class.java)
    private val loggingController = LoggingController(loggingService)

    @Test
    fun logMessageReturnsOkStatus() {
        val logDto = LogDto(serviceName = "TestService", message = "Test message")
        doNothing().`when`(loggingService).logMessage(logDto.serviceName, logDto.message)

        val response: ResponseEntity<Void> = loggingController.logMessage(logDto)

        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun logMessageThrowsExceptionWhenServiceFails() {
        val logDto = LogDto(serviceName = "TestService", message = "Test message")
        doThrow(RuntimeException::class.java).`when`(loggingService).logMessage(logDto.serviceName, logDto.message)

        assertThrows(RuntimeException::class.java) {
            loggingController.logMessage(logDto)
        }
    }
}