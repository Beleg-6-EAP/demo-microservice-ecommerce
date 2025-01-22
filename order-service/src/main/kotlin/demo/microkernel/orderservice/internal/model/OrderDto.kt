package demo.microkernel.orderservice.internal.model


data class OrderDto(
    val userId: String,
    val amount: Double,
    val status: String,
) {
    fun map() = Order(
        userId = userId,
        amount = amount,
        status = status,
    )
}