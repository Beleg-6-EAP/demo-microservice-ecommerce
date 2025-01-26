package demo.microkernel.orderservice.controller

import demo.microkernel.orderservice.internal.model.Order
import demo.microkernel.orderservice.internal.model.OrderDto
import demo.microkernel.orderservice.service.OrderService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class OrderControllerTest {

    private val orderService: OrderService = mock(OrderService::class.java)
    private val orderController = OrderController(orderService)

    @Test
    fun getAllReturnsListOfOrders() {
        val orders = listOf(Order(), Order())
        `when`(orderService.getAll()).thenReturn(orders)

        val response: ResponseEntity<List<Order?>> = orderController.getAll()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(orders, response.body)
    }

    @Test
    fun createOrderReturnsCreatedStatus() {
        val orderDto = OrderDto(
            userId = "123",
            amount = 12.0,
            status = "success"
        )
        doReturn(Order()).`when`(orderService).create(any())

        val response: ResponseEntity<Void> = orderController.createOrder(orderDto)

        assertEquals(HttpStatus.CREATED, response.statusCode)
    }

    @Test
    fun getAllReturnsEmptyListWhenNoOrders() {
        `when`(orderService.getAll()).thenReturn(emptyList())

        val response: ResponseEntity<List<Order?>> = orderController.getAll()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(emptyList<Order>(), response.body)
    }
}