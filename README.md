# Description
Microservices playground to emulate Phone App backend, which consists of the following components:
- Phones Service
- Phones DB
- Orders Service
- Orders DB

# Technologies
- Docker
- Spring Boot
- Spring Web Reactive (Orders Service)
- MySQL
- MongoDB

# Phones Service

## Get all phones
Retrieves all phones available in the system.

PATH: `/v1/phones`

HTTP Method: GET

### Example
```
http://localhost:8082/v1/phones
```

### Response codes
| Status code | Description |
| --- | --- |
| 200 OK | Returns list of all phones available in the system |
| 4xx | You did something wrong. |
| 5xx | Something went wrong on the server. |

#### Response Body

```
{
    "phones": [
        {
            "description": "Super Retina in two sizes â€” including the largest display ever on an iPhone. Even faster Face ID. The smartest, most powerful chip in a smartphone. And a breakthrough dual-camera system. iPhone XS is everything you love about iPhone. Taken to the extreme.",
            "imageUrl": "https://www.apple.com/iphone-xs/",
            "name": "Iphone X",
            "phoneId": 1,
            "price": 900.99
        },
        {
            "description": "Galaxy Note9 puts powerful technology in the hands of pioneers who demand more. Innovative features and design make it the only phone to keep up with the next generation of achievers.",
            "imageUrl": "https://www.samsung.com/us/mobile/galaxy-note9/",
            "name": "Galaxy Note9",
            "phoneId": 2,
            "price": 899.99
        }
    ]
}
```

# Orders Service

## Create order
Creates order with calculated total amount.

PATH: `/v1/orders`

HTTP Method: POST

### Example
```
http://localhost:9091/v1/phones
{
  "firstName": "Tony",
  "lastName": "Tester",
  "email": "tt@test.com",
  "phoneIds": [
    1,
    2
  ]
}
```

### Response codes
| Status code | Description |
| --- | --- |
| 200 OK | Returns list of all phones available in the system |
| 4xx | You did something wrong. |
| 5xx | Something went wrong on the server. |

#### Response Body

```
{
    "email": "tt@test.com",
    "firstName": "Tony",
    "lastName": "Tester",
    "phoneIds": [
        1,
        2
    ],
    "totalPrice": 1800.98
}
```

# Testing

## Unit tests
Each service has unit tests, which you can run in this way:
`$ ./mvnw test`

## Integration tests
Component and integration tests can be added like I did in my previous [project](https://github.com/isaranchuk/integration-testing-demo).

# Development
Use `docker-compose` to set up development environment:
1. `$ ./build-image.sh`
2. `$ docker-compose up`

# What should be improved?
1. Get Phones API should have paging and not return all phones at once.
2. Get Phones API should have filter to return only phones we're interested in.
3. Get Phones API is a good candidate for caching.
4. If Create Order process will become more complex, time consuming or will rely on not reliable third party then it would make sense to introduce Queue.