## Requirements
* Maven
* Java 21
* Docker

## Compile
* mvn clean compile package

## Docker local
docker build -t book-app -f .docker\Dockerfile.jvm
<br>docker run -it -p 80:80 book-app