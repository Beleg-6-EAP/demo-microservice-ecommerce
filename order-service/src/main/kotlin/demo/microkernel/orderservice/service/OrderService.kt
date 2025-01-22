package demo.microkernel.orderservice.service

import demo.microkernel.orderservice.internal.model.Order


interface OrderService {
    fun create(order: Order): Order
    fun getAll(): List<Order?>
}