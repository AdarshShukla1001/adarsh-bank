#!/bin/bash
curl -X POST http://localhost:8080/api/accounts \
-H "Content-Type: application/json" \
-d '{
    "accountHolderName": "Astha Shukla",
    "balance": 5000.0
}'
echo -e "\n"
