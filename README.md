# Course Search Application with Spring Boot and Elasticsearch

## Elasticsearch Setup (Quick Start)

This guide helps you set up Elasticsearch locally using Docker Compose.

### Prerequisites
Make sure you have Docker and Docker Compose installed:

```bash
docker --version
docker-compose --version
```

### Step 1: Create `docker-compose.yml`
In your project root, create a file named `docker-compose.yml`

```yml
version: '3.8'

services:
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.13.4
    container_name: elasticsearch
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false
    ports:
      - 9200:9200
```

### Step 2: Start ElasticSearch
Run the following to start:

```bash
docker-compose up -d
```

### Step 3: Verify it's Running
Check if it's running:

```bash
curl http://localhost:9200
```

You should see a JSON response. If not, ensure xpack.security.enabled=false is present.

### Step 4: Stop Elasticsearch
When done, stop it with:

```bash
docker-compose down
```

## Application Configuration
To connect your Spring Boot application with the locally running Elasticsearch instance, add the following properties to your `src/main/resources/application.properties` file:

```properties
spring.data.elasticsearch.client.reactive.endpoints=localhost:9200
spring.data.elasticsearch.repositories.enabled=true
```

### Explanation
`endpoints=localhost:9200` tells the application where Elasticsearch is running.
`repositories.enabled=true` allows Spring Data to discover Elasticsearch repositories automatically.

With this configuration, your Spring Boot application should connect to Elasticsearch without requiring any further changes.
