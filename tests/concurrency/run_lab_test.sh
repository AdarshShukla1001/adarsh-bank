#!/bin/bash

# ANSI colors for better visibility
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}--- Step 1: Setting up 100 accounts ---${NC}"
curl -s -X POST http://localhost:8080/api/lab/setup
echo -e "\n"

echo -e "${BLUE}--- Step 2: Initial Total Money ---${NC}"
INITIAL_JSON=$(curl -s -X GET http://localhost:8080/api/lab/total-money)
echo "$INITIAL_JSON"
# Extract number using grep/sed (assuming format: Current Total Money in Bank: $100000.0)
INITIAL_VALUE=$(echo "$INITIAL_JSON" | grep -oE '[0-9]+\.[0-9]+')

echo -e "\n${BLUE}--- Step 3: Running Race Condition Simulation (1000 parallel transfers) ---${NC}"
curl -s -X POST http://localhost:8080/api/lab/run-race
echo -e "\n"

echo -e "${BLUE}--- Step 4: Final Total Money ---${NC}"
FINAL_JSON=$(curl -s -X GET http://localhost:8080/api/lab/total-money)
echo "$FINAL_JSON"
FINAL_VALUE=$(echo "$FINAL_JSON" | grep -oE '[0-9]+\.[0-9]+')

echo -e "\n${BLUE}--- Final Analysis ---${NC}"
DIFF=$(echo "$FINAL_VALUE - $INITIAL_VALUE" | bc -l)

if (( $(echo "$DIFF == 0" | bc -l) )); then
    echo -e "${GREEN}Result: SUCCESS (Total money is stable)${NC}"
else
    echo -e "${RED}Result: RACE CONDITION DETECTED!${NC}"
    echo -e "${RED}Exact Difference: \$$DIFF${NC}"
    echo -e "Explanation: Because we have no locks, multiple threads updated the same account at once."
    echo -e "Some transfers were overwritten, causing money to be 'created' or 'lost' in the bank."
fi
echo -e "\n"
