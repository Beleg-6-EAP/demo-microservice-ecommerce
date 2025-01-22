package demo.microkernel.orderservice.client

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient


@Component
internal class RestClient {
    private val restClient = RestClient.builder()
        .baseUrl("http://localhost:8080")
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
}