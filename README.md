# Redis-Caching-Practice

A Spring Boot project demonstrating how to use Redis as a caching solution for RESTful APIs. This example features a simple product service with caching enabled for improved performance and scalability.

## 🚀 Features

- **Spring Boot REST API** for product management
- **Redis integration** for caching frequently accessed data
- **Docker Compose** setup for running Redis locally
- Example configuration for cacheable endpoints

## 🛠️ Getting Started

### Prerequisites

- Java 17+
- Maven
- Docker (for Redis)

### Running Redis with Docker

Start Redis using Docker Compose:

```sh
docker-compose -f docker-compose/docker-compose.yaml up -d
```

### Configuration

Redis connection settings are located in `src/main/resources/application.properties`:

```properties
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379
```

### Build and Run

Build and start the Spring Boot application:

```sh
mvn clean install
mvn spring-boot:run
```

## 📦 API Endpoints

- `GET /products` — List all products (cached)
- `GET /products/{id}` — Get product by ID (cached)
- `POST /products` — Add a new product

## ⚡ How Caching Works

- Product data is cached in Redis to reduce database load.
- Cache is automatically updated when products are added or modified.
- Cache eviction and TTL (time-to-live) can be configured in the application properties.

## 📁 Project Structure

```
product-service/
  └── src/
      └── main/
          └── java/
          └── resources/
              └── application.properties
docker-compose/
  └── docker-compose.yaml
```
