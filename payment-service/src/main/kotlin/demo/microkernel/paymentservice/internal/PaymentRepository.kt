package demo.microkernel.paymentservice.internal

import demo.microkernel.paymentservice.internal.model.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository: JpaRepository<Payment, String>