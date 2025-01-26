package demo.microkernel.paymentservice.controller

import demo.microkernel.paymentservice.internal.model.Payment
import demo.microkernel.paymentservice.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/payments")
class PaymentController(
    private val paymentService: PaymentService
) {
    @PostMapping
    fun processPayment(@RequestBody orderId: String): ResponseEntity<Void> {
        paymentService.processPayment(orderId)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<Payment>>
        = ResponseEntity.ok(paymentService.getAll())
}