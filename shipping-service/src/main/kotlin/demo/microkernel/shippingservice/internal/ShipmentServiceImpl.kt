package demo.microkernel.shippingservice.internal

import demo.microkernel.shippingservice.client.RestClient
import demo.microkernel.shippingservice.internal.model.Shipment
import demo.microkernel.shippingservice.service.ShipmentService
import org.springframework.stereotype.Service

@Service
internal class ShipmentServiceImpl(
    private val shipmentRepository: ShipmentRepository,
    private val restClient: RestClient,
) : ShipmentService {
    override fun handleShipping(orderId: String) {
        val shipment = Shipment(orderId = orderId)
        restClient.postLog("Shipment processed for order: $orderId")
        shipmentRepository.save(shipment)
    }

    override fun getAll(): List<Shipment> {
        return shipmentRepository.findAll()
    }
}