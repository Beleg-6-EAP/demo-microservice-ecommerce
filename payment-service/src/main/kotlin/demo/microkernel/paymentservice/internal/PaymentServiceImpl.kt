package demo.microkernel.paymentservice.internal

import demo.microkernel.paymentservice.client.RestClient
import demo.microkernel.paymentservice.internal.model.Payment
import demo.microkernel.paymentservice.service.PaymentService
import org.springframework.stereotype.Service

@Service
internal class PaymentServiceImpl(
    private val paymentRepository: PaymentRepository,
    private val restClient: RestClient,
) : PaymentService {
    override fun processPayment(orderId: String) {
        val payment = Payment(orderId = orderId)
        restClient.postLog("Payment processed for order: $orderId")
        paymentRepository.save(payment)
    }

    override fun getAll(): List<Payment> =
        paymentRepository.findAll()
}