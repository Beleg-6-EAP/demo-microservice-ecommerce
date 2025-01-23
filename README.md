![Static Badge](https://img.shields.io/badge/Kotin-1.9.25-orange)
![Static Badge](https://img.shields.io/badge/Maven-4.0.0-red)
[![Maven Build & Tests](https://github.com/Beleg-6-EAP/demo-microservice-ecommerce/actions/workflows/maven-build-test.yml/badge.svg)](https://github.com/Beleg-6-EAP/demo-microservice-ecommerce/actions/workflows/maven-build-test.yml)


# Microservices E-Commerce Demo

This repository provides a minimal example showcasing the Microservices-Architecture in an E-Commerce setting.
This setting includes orders, payment and shipping.

This demo is part of an article on Enterprise Architecture-Patterns.
The article, including the complete explanation of the E-Commerce-Example, can be found [here](https://github.com/Beleg-6-EAP/Belegarbeit).

## Get running

Running this demo requires the use of `docker-compose` since it is based on multiple isolated services:
```bash
bash> docker-compose up
```

This will start a web-server running on `http://localhost:8080` with the following endpoints:

- CreateOrder: `POST /api/orders`
- AllOrders: `GET /api/orders`
- AllPayments: `GET /api/payments`
- AllShipments: `GET /api/shipments`

We start with an empty database.
To create an order `POST` below request to `http://localhost:8080/api/orders`:

```json
{
    "userId": "1234-5678-8765-4321",
    "amount": 42.0,
    "status": "New"
}
```

This will initiate payment as well as shipment.

Its success can be checked by requesting the other endpoints via `GET`.

## Troubleshooting

If there is any trouble or if you have any questions, feel free to open an issue!
