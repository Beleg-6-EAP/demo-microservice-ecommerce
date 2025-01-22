package demo.microkernel.shippingservice.internal

import demo.microkernel.shippingservice.internal.model.Shipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShipmentRepository: JpaRepository<Shipment, String>