package demo.microkernel.shippingservice.service

import demo.microkernel.shippingservice.internal.model.Shipment

interface ShipmentService {
    fun handleShipping(orderId: String)
    fun getAll(): List<Shipment>
}