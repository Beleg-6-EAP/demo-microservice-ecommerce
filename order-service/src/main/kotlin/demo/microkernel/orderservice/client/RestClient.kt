package demo.microkernel.orderservice.client

import demo.microkernel.orderservice.internal.model.LogDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient


@Component
internal class RestClient {
    private val restClient = RestClient.builder()
        .baseUrl("http://api-gateway:8080")
        .build()

    fun postPayment(orderId: String): ResponseEntity<Void> =
        restClient.post()
            .uri("/api/payments")
            .body(orderId)
            .retrieve()
            .toBodilessEntity()

    fun postShipment(orderId: String): ResponseEntity<Void> =
        restClient.post()
            .uri("/api/shipments")
            .body(orderId)
            .retrieve()
            .toBodilessEntity()

    fun postLog(message: String): ResponseEntity<Void> =
        restClient.post()
            .uri("/log")
            .body(LogDto(serviceName = "order-service", message = message))
            .retrieve()
            .toBodilessEntity()
}