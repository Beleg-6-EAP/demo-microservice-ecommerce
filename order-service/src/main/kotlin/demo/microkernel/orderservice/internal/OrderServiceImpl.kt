package demo.microkernel.orderservice.internal

import demo.microkernel.orderservice.client.RestClient
import demo.microkernel.orderservice.internal.model.Order
import demo.microkernel.orderservice.service.OrderService
import org.springframework.stereotype.Service


@Service
internal class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val restClient: RestClient,
) : OrderService {

    override fun create(order: Order): Order {
        val savedOrder: Order = orderRepository.save(order)
        restClient.postPayment(savedOrder.id)
        restClient.postShipment(savedOrder.id)
        return savedOrder
    }

    override fun getAll(): List<Order> =
        orderRepository.findAll()
}