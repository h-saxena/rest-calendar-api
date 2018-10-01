## Start the application
```
$mvn clean spring-boot:run
```

## API docs
http://localhost:8080/v2/api-docs

## Swagger UI to test the application
http://localhost:8080/swagger-ui.html

## Pre-loaded data
data is loaded by ../src/main/resources/data.sql



#Security

## Generate the token

POST http://localhost:8080/GetToken
{
    "clientId" : "",
    "Secret" : "base64EncodedText"
}

##

curl -X GET   http://localhost:8080/api/xxxxxxx  -H 'Authorization: Bearer <token-generated>'