package demo.microkernel.shippingservice.service

import demo.microkernel.shippingservice.client.RestClient
import demo.microkernel.shippingservice.internal.ShipmentRepository
import demo.microkernel.shippingservice.internal.ShipmentServiceImpl
import demo.microkernel.shippingservice.internal.model.Shipment
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class ShipmentServiceImplTest {

    private val shipmentRepository: ShipmentRepository = mock(ShipmentRepository::class.java)
    private val restClient: RestClient = mock(RestClient::class.java)
    private val shipmentService: ShipmentService = ShipmentServiceImpl(shipmentRepository, restClient)

    @Test
    fun handleShippingSavesShipmentAndPostsToRestClient() {
        val orderId = "12345"
        val shipment = Shipment(orderId = orderId)
        `when`(shipmentRepository.save(shipment)).thenReturn(shipment)

        shipmentService.handleShipping(orderId)

        verify(restClient).postLog("Shipment processed for order: $orderId")
        verify(shipmentRepository).save(shipment)
    }

    @Test
    fun getAllReturnsListOfShipments() {
        val shipments = listOf(Shipment(orderId = "12345"), Shipment(orderId = "67890"))
        `when`(shipmentRepository.findAll()).thenReturn(shipments)

        val result = shipmentService.getAll()

        assertEquals(shipments, result)
    }

    @Test
    fun getAllReturnsEmptyListWhenNoShipments() {
        `when`(shipmentRepository.findAll()).thenReturn(emptyList())

        val result = shipmentService.getAll()

        assertEquals(emptyList<Shipment>(), result)
    }
}