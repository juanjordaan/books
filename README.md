## Requirements
* Maven
* Java 21
* Docker

## Compile
* mvn clean compile package

## Database
This app uses H2, it is created on startup when starting.
<br>Liquibase will create the required data.

## Rest Endpoints
There is a function that prints all the exposed endpoints on startup.

## Docker local
docker build -t book-app -f .docker\Dockerfile.jvm
<br>docker run -it -p 80:80 book-app