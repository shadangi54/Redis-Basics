Redis-Caching-Practice
This project demonstrates how to use Redis as a caching solution in a Spring Boot application. It provides a simple product service with caching enabled for improved performance and scalability.

Features
Spring Boot REST API for product management
Redis integration for caching frequently accessed data
Docker Compose setup for running Redis locally
Example configuration for cacheable endpoints
Getting Started
Prerequisites
Java 17+
Maven
Docker (for Redis)
Running Redis with Docker
Configuration
Redis connection settings are in src/main/resources/application.properties:

Build and Run
API Endpoints
GET /products - List all products (cached)
GET /products/{id} - Get product by ID (cached)
POST /products - Add a new product
How Caching Works
Product data is cached in Redis to reduce database load.
Cache is automatically updated when products are added or modified.
Cache eviction and TTL can be configured in the application properties.
