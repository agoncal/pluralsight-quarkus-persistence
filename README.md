# Vintage Store

```bash
curl http://localhost:8083/api/authors | jq

curl http://localhost:8083/api/musicians | jq

curl http://localhost:8083/api/publishers | jq

curl -X POST http://localhost:8083/api/publishers \
  -H "Content-Type: application/json" \
  -d '{
    "name": "My New Publisher",
    "country": "USA",
    "website": "https://mynewpublisher.com",
    "foundedYear": 2024
    }'
```
