package demo.microkernel.orderservice.service

import demo.microkernel.orderservice.client.RestClient
import demo.microkernel.orderservice.internal.OrderRepository
import demo.microkernel.orderservice.internal.OrderServiceImpl
import demo.microkernel.orderservice.internal.model.Order
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class OrderServiceImplTest {

    private val orderRepository: OrderRepository = mock(OrderRepository::class.java)
    private val restClient: RestClient = mock(RestClient::class.java)
    private val orderService: OrderService = OrderServiceImpl(orderRepository, restClient)

    @Test
    fun createOrderSavesOrderAndPostsToRestClient() {
        val order = Order()
        val savedOrder = Order(id = "1", userId = "2")
        `when`(orderRepository.save(order)).thenReturn(savedOrder)

        val result = orderService.create(order)

        assertEquals(savedOrder, result)
        verify(restClient).postPayment(savedOrder.id)
        verify(restClient).postShipment(savedOrder.id)
        verify(restClient).postLog("Order created: ${savedOrder.id} for customer: ${savedOrder.userId}")
    }

    @Test
    fun getAllReturnsListOfOrders() {
        val orders = listOf(Order(), Order())
        `when`(orderRepository.findAll()).thenReturn(orders)

        val result = orderService.getAll()

        assertEquals(orders, result)
    }

    @Test
    fun getAllReturnsEmptyListWhenNoOrders() {
        `when`(orderRepository.findAll()).thenReturn(emptyList())

        val result = orderService.getAll()

        assertEquals(emptyList<Order>(), result)
    }
}