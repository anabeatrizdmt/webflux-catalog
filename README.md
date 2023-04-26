# WebFlux Catalog API

This project is a reactive RESTful API for managing a product catalog. It is written in Java and uses reactive programming with Spring WebFlux and MongoDB.

**Note:** This API is part of a larger e-commerce system that includes user management and order management. To run the complete system, you must also run the [WebFlux Users API](https://github.com/anabeatrizdmt/webflux-users), the [WebFlux Orders API](https://github.com/anabeatrizdmt/webflux-orders), and the [WebFlux E-commerce API Gateway](https://github.com/anabeatrizdmt/webflux-ecommerce-api-gateway). The API Gateway provides a unified interface to the entire system and manages communication between the individual APIs.


## Table of Contents

- [Requirements](#requirements)
- [Installation and Setup](#installation-and-setup)
- [Usage](#usage)
  - [Create a product](#create-a-product)
  - [Get all products](#get-all-products)
  - [Get a product by ID](#get-a-product-by-id)
  - [Check product stock](#check-product-stock)
  - [Update product stock](#update-product-stock)


## Requirements

- Java 11 or higher
- MongoDB

## Installation and Setup

1. Clone the repository:

```
git clone https://github.com/anabeatrizdmt/webflux-catalog.git
```

2. Install dependencies:

```
cd webflux-catalog
mvn install
```

3. Run the application:

```
mvn spring-boot:run
```

## Usage

### Create a product

To create a new product, send a `POST` request to `http://localhost:8080/catalog` with a JSON payload in the following format:

```
{
"name": "Product Name",
"price": 10.99,
"availableQuantity": 100
}
```

### Get all products

To retrieve a list of all products, send a `GET` request to `http://localhost:8080/catalog`.

### Get a product by ID

To retrieve a specific product by its ID, send a `GET` request to `http://localhost:8080/catalog/{id}`, where `{id}` is the ID of the product you wish to retrieve.

### Check product stock

To check the stock of one or more products, send a `POST` request to `http://localhost:8081/catalog/stock` with a JSON payload in the following format:

```
[
"product-id-1",
"product-id-2",
...
]
```

### Update product stock

To update the stock of one or more products, send a `POST` request to `http://localhost:8080/catalog/update-stock` with a JSON payload in the following format:

```
[
{
"productId": "product-id-1",
"purchasedQuantity": 1
},
{
"productId": "product-id-2",
"purchasedQuantity": 3
},
...
]
```
