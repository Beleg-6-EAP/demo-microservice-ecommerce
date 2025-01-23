package demo.microkernel.shippingservice.client

import demo.microkernel.shippingservice.internal.model.LogDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient


@Component
internal class RestClient {
    private val restClient = RestClient.builder()
        .baseUrl("http://api-gateway:8080")
        .build()

    fun postLog(message: String): ResponseEntity<Void> =
        restClient.post()
            .uri("/log")
            .body(LogDto(serviceName = "shipping-service", message = message))
            .retrieve()
            .toBodilessEntity()
}