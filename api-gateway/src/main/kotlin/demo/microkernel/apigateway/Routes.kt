package demo.microkernel.apigateway

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Routes {
    @Bean
    fun clientPortal(routeLocatorBuilder: RouteLocatorBuilder) =
        routeLocatorBuilder.routes {
            route(id = "order-service") {
                path("/api/orders/**")
                uri("http://order-service:8081")
            }
            route(id = "payment-service") {
                path("/api/payments/**")
                uri("http://payment-service:8082")
            }
            route(id = "shipping-service") {
                path("/api/shipments/**")
                uri("http://shipping-service:8083")
            }
        }

}