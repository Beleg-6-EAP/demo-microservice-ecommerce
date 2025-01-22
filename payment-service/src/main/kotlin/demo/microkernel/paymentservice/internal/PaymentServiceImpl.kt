package demo.microkernel.paymentservice.internal

import demo.microkernel.paymentservice.internal.model.Payment
import demo.microkernel.paymentservice.service.PaymentService
import org.springframework.stereotype.Service

@Service
class PaymentServiceImpl(
    private val paymentRepository: PaymentRepository,
) : PaymentService {
    override fun processPayment(orderId: String) {
        val payment = Payment(orderId = orderId)
        paymentRepository.save(payment)
    }

    override fun getAll(): List<Payment> =
        paymentRepository.findAll()
}