package demo.microkernel.shippingservice.internal

import demo.microkernel.shippingservice.internal.model.Shipment
import demo.microkernel.shippingservice.service.ShipmentService
import org.springframework.stereotype.Service

@Service
class ShipmentServiceImpl(
    private val shipmentRepository: ShipmentRepository
) : ShipmentService {
    override fun handleShipping(orderId: String) {
        val shipment = Shipment(orderId = orderId)
        shipmentRepository.save(shipment)
    }

    override fun getAll(): List<Shipment> {
        return shipmentRepository.findAll()
    }
}