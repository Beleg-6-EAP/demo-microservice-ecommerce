package demo.microkernel.orderservice.controller

import demo.microkernel.orderservice.internal.model.Order
import demo.microkernel.orderservice.internal.model.OrderDto
import demo.microkernel.orderservice.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Order?>> =
        ResponseEntity.ok(orderService.getAll())

    @PostMapping
    fun createOrder(@RequestBody order: OrderDto): ResponseEntity<Void> {
        orderService.create(order.map())
        return ResponseEntity(HttpStatus.CREATED)
    }
}