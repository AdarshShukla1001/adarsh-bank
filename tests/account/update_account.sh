#!/bin/bash
# Replace '1' with the ID you want to update
curl -X PUT http://localhost:8080/api/accounts/1 \
-H "Content-Type: application/json" \
-d '{
    "accountHolderName": "Adarsh Shukla Updated",
    "balance": 9999.99
}'
echo -e "\n"
