# mcs-case-study-cloud

## Local

**List Customers**

curl --location --request GET 'http://localhost:8100/customers/'

**Create Customer**

curl --location --request POST 'http://localhost:8100/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Customer 4",
    "lastName": "C4",
    "email": "c4@demo.com"
}'

curl --location --request POST 'http://localhost:8100/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Customer 55",
    "lastName": "C55",
    "email": "c55@demo.com"
}'

curl --location --request POST 'http://localhost:8100/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Customer 6",
    "lastName": "C6",
    "email": "c6@demo.com"
}'

**Update Customer**

curl --location --request PUT 'http://localhost:8100/customers/5' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 5,
    "firstName": "Customer 5",
    "lastName": "C5",
    "email": "c5@demo.com"
}'

**Validate Customer**

curl --location --request POST 'http://localhost:8102/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderDescription": "Second Order",
    "orderDate": "2020-09-10",
    "customerId": 60,
    "itemNames": [
        "Mobile",
        "Tab"
    ]
}'

**Validate Item**

curl --location --request POST 'http://localhost:8102/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderDescription": "First Order",
    "orderDate": "2020-09-10",
    "customerId": 4,
    "itemNames": [
        "HeadPhone",
        "Tab"
    ]
}'

**Create Order**

curl --location --request POST 'http://localhost:8102/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderDescription": "First Order",
    "orderDate": "2020-09-10",
    "customerId": 4,
    "itemNames": [
        "Mobile",
        "Tab"
    ]
}'

curl --location --request POST 'http://localhost:8102/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderDescription": "Second Order",
    "orderDate": "2020-09-10",
    "customerId": 5,
    "itemNames": [
        "TV",
        "Mobile",
        "Tab"
    ]
}'

curl --location --request POST 'http://localhost:8102/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderDescription": "Third Order",
    "orderDate": "2020-09-10",
    "customerId": 6,
    "itemNames": [
        "TV",
        "TV",
        "Mobile",
        "Mobile",
        "Tab"
    ]
}'

**Customer DB**         -   http://localhost:8100/h2-console

**Item DB**             -   http://localhost:8101/h2-console

**Sales Order DB**      -   http://localhost:8102/h2-console

**Eureka**              -   http://localhost:8761/

**Rabbit MQ**           -   http://localhost:15672/

**Circuit Breaker**     -   http://localhost:8100/customers/fault-customer

**Config refresh**      -   curl --location --request POST 'http://localhost:8100/actuator/refresh'


## Cloud

**List Customers**

curl --location --request GET 'https://customer-service-deen.cfapps.io/customers/'

**Create Customer**

curl --location --request POST 'https://customer-service-deen.cfapps.io/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Customer 4",
    "lastName": "C4",
    "email": "c4@demo.com"
}'

curl --location --request POST 'https://customer-service-deen.cfapps.io/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Customer 55",
    "lastName": "C55",
    "email": "c55@demo.com"
}'

curl --location --request POST 'https://customer-service-deen.cfapps.io/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Customer 6",
    "lastName": "C6",
    "email": "c6@demo.com"
}'

**Update Customer**

curl --location --request PUT 'https://customer-service-deen.cfapps.io/customers/5' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 5,
    "firstName": "Customer 5",
    "lastName": "C5",
    "email": "c5@demo.com"
}'

**Validate Customer**

curl --location --request POST 'https://sales-order-service-deen.cfapps.io/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderDescription": "Second Order",
    "orderDate": "2020-09-10",
    "customerId": 60,
    "itemNames": [
        "Mobile",
        "Tab"
    ]
}'

**Validate Item**

curl --location --request POST 'https://sales-order-service-deen.cfapps.io/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderDescription": "First Order",
    "orderDate": "2020-09-10",
    "customerId": 4,
    "itemNames": [
        "HeadPhone",
        "Tab"
    ]
}'

**Create Order**

curl --location --request POST 'https://sales-order-service-deen.cfapps.io/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderDescription": "First Order",
    "orderDate": "2020-09-10",
    "customerId": 4,
    "itemNames": [
        "Mobile",
        "Tab"
    ]
}'

curl --location --request POST 'https://sales-order-service-deen.cfapps.io/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderDescription": "Second Order",
    "orderDate": "2020-09-10",
    "customerId": 5,
    "itemNames": [
        "TV",
        "Mobile",
        "Tab"
    ]
}'

curl --location --request POST 'https://sales-order-service-deen.cfapps.io/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderDescription": "Third Order",
    "orderDate": "2020-09-10",
    "customerId": 6,
    "itemNames": [
        "TV",
        "TV",
        "Mobile",
        "Mobile",
        "Tab"
    ]
}'

**Customer DB**         -   https://customer-service-deen.cfapps.io/h2-console

**Item DB**             -   https://item-service-deen.cfapps.io/h2-console

**Sales Order DB**      -   https://sales-order-service-deen.cfapps.io/h2-console

**Circuit Breaker**     -   https://customer-service-deen.cfapps.io/customers/fault-customer

**Config refresh**      -   curl --location --request POST 'https://customer-service-deen.cfapps.io/actuator/refresh'

