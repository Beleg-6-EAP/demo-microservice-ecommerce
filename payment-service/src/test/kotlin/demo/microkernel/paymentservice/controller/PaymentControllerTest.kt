package demo.microkernel.paymentservice.controller

import demo.microkernel.paymentservice.internal.model.Payment
import demo.microkernel.paymentservice.service.PaymentService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class PaymentControllerTest {

    private val paymentService: PaymentService = mock(PaymentService::class.java)
    private val paymentController = PaymentController(paymentService)

    @Test
    fun processPaymentReturnsOkStatus() {
        val orderId = "12345"
        doNothing().`when`(paymentService).processPayment(orderId)

        val response: ResponseEntity<Void> = paymentController.processPayment(orderId)

        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun getAllReturnsListOfPayments() {
        val payments = listOf(Payment(), Payment())
        `when`(paymentService.getAll()).thenReturn(payments)

        val response: ResponseEntity<List<Payment>> = paymentController.getAll()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(payments, response.body)
    }

    @Test
    fun getAllReturnsEmptyListWhenNoPayments() {
        `when`(paymentService.getAll()).thenReturn(emptyList())

        val response = paymentController.getAll()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(emptyList<Payment>(), response.body)
    }
}