package demo.microkernel.shippingservice.controller

import demo.microkernel.shippingservice.internal.model.Shipment
import demo.microkernel.shippingservice.service.ShipmentService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ShippingControllerTest {

    private val shipmentService: ShipmentService = mock(ShipmentService::class.java)
    private val shipmentController = ShipmentController(shipmentService)

    @Test
    fun handleShipmentReturnsOkStatus() {
        val orderId = "12345"
        doNothing().`when`(shipmentService).handleShipping(orderId)

        val response: ResponseEntity<Void> = shipmentController.handleShipment(orderId)

        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun getAllReturnsListOfShipments() {
        val shipments = listOf(Shipment(), Shipment())
        `when`(shipmentService.getAll()).thenReturn(shipments)

        val response: ResponseEntity<List<Shipment>> = shipmentController.getAll()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(shipments, response.body)
    }

    @Test
    fun getAllReturnsEmptyListWhenNoShipments() {
        `when`(shipmentService.getAll()).thenReturn(emptyList())

        val response: ResponseEntity<List<Shipment>> = shipmentController.getAll()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(emptyList<Shipment>(), response.body)
    }
}