# Vintage Store

```bash
curl http://localhost:8082/api/activities | jq

curl http://localhost:8083/api/authors | jq
curl http://localhost:8083/api/books | jq
curl http://localhost:8083/api/cds | jq
curl http://localhost:8083/api/musicians | jq
curl http://localhost:8083/api/publishers | jq
curl http://localhost:8083/api/pos | jq
curl http://localhost:8083/api/suppliers | jq
curl http://localhost:8083/api/users | jq

curl http://localhost:8084/api/reviews | jq
```

```bash
curl -X POST http://localhost:8083/api/publishers \
  -H "Content-Type: application/json" \
  -d '{
    "name": "My New Publisher",
    "country": "USA",
    "website": "https://mynewpublisher.com",
    "foundedYear": 2024
    }'
```
