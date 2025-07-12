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

## Bulk Indexing Sample Data
When the Spring Boot application starts, it automatically reads the sample-courses.json file (located in src/main/resources) and indexes all course documents into the courses index in Elasticsearch.

### How it works
- The CourseDataLoader class is a startup listener (@Component) that runs on application startup.

- It uses Jackson's ObjectMapper to parse sample-courses.json into a list of CourseDocument objects.

- The parsed documents are then saved into Elasticsearch using CourseRepository.

### To Trigger Ingestion
You don’t need to do anything manually. Just run the application, and the ingestion will happen automatically.

If successful, your console/log will show:
``Courses indexed successfully into Elasticsearch``

### To Verify It Worked
You can use curl or Postman to verify that the data is indexed.

```bash
curl "http://localhost:9200/courses/_search?size=50&pretty"
```
You should see a JSON response containing the indexed courses.