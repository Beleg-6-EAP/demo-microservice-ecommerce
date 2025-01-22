package demo.microkernel.orderservice.internal

import demo.microkernel.orderservice.internal.model.Order
import demo.microkernel.orderservice.service.OrderService
import org.springframework.stereotype.Service


@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository
) : OrderService {

    override fun create(order: Order): Order {
        val savedOrder: Order = orderRepository.save(order)

        // TODO:
//        paymentService.processPayment(savedOrder.getId())
//        shipmentService.handleShipping(savedOrder.getId())

        return savedOrder
    }

    override fun getAll(): List<Order> =
        orderRepository.findAll()
}