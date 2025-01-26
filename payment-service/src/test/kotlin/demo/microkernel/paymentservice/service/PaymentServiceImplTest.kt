package demo.microkernel.paymentservice.service

import demo.microkernel.paymentservice.client.RestClient
import demo.microkernel.paymentservice.internal.PaymentRepository
import demo.microkernel.paymentservice.internal.PaymentServiceImpl
import demo.microkernel.paymentservice.internal.model.Payment
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class PaymentServiceImplTest {

    private val paymentRepository: PaymentRepository = mock(PaymentRepository::class.java)
    private val restClient: RestClient = mock(RestClient::class.java)
    private val paymentService: PaymentService = PaymentServiceImpl(paymentRepository, restClient)

    @Test
    fun processPaymentSavesPaymentAndPostsToRestClient() {
        val orderId = "12345"
        val payment = Payment(orderId = orderId)
        `when`(paymentRepository.save(payment)).thenReturn(payment)

        paymentService.processPayment(orderId)

        verify(restClient).postLog("Payment processed for order: $orderId")
        verify(paymentRepository).save(payment)
    }

    @Test
    fun getAllReturnsListOfPayments() {
        val payments = listOf(Payment(orderId = "12345"), Payment(orderId = "67890"))
        `when`(paymentRepository.findAll()).thenReturn(payments)

        val result = paymentService.getAll()

        assertEquals(payments, result)
    }

    @Test
    fun getAllReturnsEmptyListWhenNoPayments() {
        `when`(paymentRepository.findAll()).thenReturn(emptyList())

        val result = paymentService.getAll()

        assertEquals(emptyList<Payment>(), result)
    }
}