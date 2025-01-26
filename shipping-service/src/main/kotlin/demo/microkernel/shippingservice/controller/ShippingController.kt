package demo.microkernel.shippingservice.controller

import demo.microkernel.shippingservice.internal.model.Shipment
import demo.microkernel.shippingservice.service.ShipmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/shipments")
class ShipmentController(
    private val shipmentService: ShipmentService
) {
    @PostMapping
    fun handleShipment(@RequestBody orderId: String): ResponseEntity<Void> {
        shipmentService.handleShipping(orderId)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<Shipment>> =
        ResponseEntity.ok(shipmentService.getAll())
}