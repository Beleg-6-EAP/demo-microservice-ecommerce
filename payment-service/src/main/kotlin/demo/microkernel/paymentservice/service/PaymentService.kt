package demo.microkernel.paymentservice.service

import demo.microkernel.paymentservice.internal.model.Payment

interface PaymentService {
    fun processPayment(orderId: String)
    fun getAll(): List<Payment>
}