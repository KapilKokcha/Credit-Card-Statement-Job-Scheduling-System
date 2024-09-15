# Credit Card Statement Job Scheduling System
## Problem Statement

The current system for generating monthly credit card statements is becoming increasingly inefficient, taking over 60 hours to process statements for 40,000 customers. With the company planning to expand its customer base to several million, the existing approach is unsustainable. A new system is required to handle this growing scale while significantly reducing processing time.

## Proposed Solution

We designed a scalable system using three Spring Boot servers with Kafka and MySQL as the backbone:

1. **Server 1 - Scheduler**:
   - Fetches customer data from the MySQL database at midnight (12:00 AM) every day using the `@Scheduled` annotation in Spring Boot.
   - Pushes the fetched data to a Kafka message queue.

2. **Server 2 and Server 3 - Worker Servers**:
   - These servers consume the data from Kafka.
   - Generate PDF statements for each user and email them to their respective email addresses.
   - This distributed system allows parallel processing, reducing the overall time taken for statement generation and distribution.

By distributing the workload across multiple servers and utilizing Kafka for message queuing, the system can handle millions of customers efficiently.

## Prerequisites

- **MySQL**: You need to set up MySQL locally or use a managed service.
- **Kafka**: Set up Kafka on your local machine or use a Kafka service for message brokering.
- **Java 11** or later.
- **Maven** (for building the Spring Boot applications).

## Setup Guide

### 1. MySQL Setup

- Install MySQL on your local machine.
- Create a new database:
  
```
CREATE DATABASE pdf_gen
```
- Update MySQL credentials in each server's application.properties file:
```
spring.datasource.url=jdbc:mysql://localhost:3306/pdf_gen
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
```

### 2. Kafka Setup
- Install Kafka locally by following the https://kafka.apache.org/quickstart
- Once installed, start Kafka and Zookeeper using the following commands:
```
# Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka Broker
bin/kafka-server-start.sh config/server.properties
```
- Create the necessary Kafka topic:
```
bin/kafka-topics.sh --create --topic PDF_GEN --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

### 3. Running the Servers
After configuring MySQL and Kafka, follow these steps to run each server:

## Server 1 - Scheduler
- Navigate to the ```com.pdf```.
- Build the application:
```
./mvnw clean package
./mvnw spring-boot:run
```
This server will fetch customer data from MySQL at midnight and push it to the Kafka queue.

## Server 2 & 3 - Worker Servers
- Navigate to the ```mail.service``` and ```sec.com.mail```
- Build the application:
```
./mvnw clean package
./mvnw spring-boot:run
```
Both servers will consume messages from the Kafka queue, generate PDF statements, and email them to users.

### 4. Monitoring and Logs
- Kafka topics and logs can be monitored using Kafka’s built-in tools.
- MySQL can be monitored using tools like MySQL Workbench.

### 5. Scalling
- To handle increasing load, additional worker servers (like Server 2 and 3) can be added to consume from Kafka and handle the statement generation in parallel.

## Contributing

Feel free to open issues or submit pull requests if you'd like to contribute to this project.
