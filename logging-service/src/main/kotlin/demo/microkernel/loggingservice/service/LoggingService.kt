package demo.microkernel.loggingservice.service

interface LoggingService {
    fun logMessage(serviceName: String, message: String)
}