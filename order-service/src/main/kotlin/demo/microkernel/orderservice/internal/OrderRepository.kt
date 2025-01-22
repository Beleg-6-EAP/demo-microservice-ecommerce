package demo.microkernel.orderservice.internal

import demo.microkernel.orderservice.internal.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface OrderRepository : JpaRepository<Order, String>