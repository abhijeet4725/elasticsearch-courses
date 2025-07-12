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

