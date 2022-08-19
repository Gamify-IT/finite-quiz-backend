# Singlechoice-Backend
This repo serves to persist the singlechoice data in a db and to communicate with different Microservices.


# Development
## Getting started
Make sure you have the following installed:

- Java: [JDK 1.17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or higher
- Maven: [Maven 3.6.3](https://maven.apache.org/download.cgi)
- Docker: [Docker latest or higher](https://www.docker.com/)

First you have to change the spring.datasource.username and the spring.datasource.password in the application.properties file. If you changed the properties of the postgres db, you also have to change spring.datasource.url.

### Run

#### Docker-compose

Start all dependencies with our docker-compose files.
Check the [manual for starting the dependencies with docker-compose](https://github.com/Gamify-IT/docs/blob/main/dev-manuals/docker-compose/docker-compose.md).

#### Project build

```sh
mvn install
```
in the folder of the project.
Go to the target folder and run 
```sh
java -jar crossword-service-0.0.1-SNAPSHOT.jar
```

### With Docker

Build the Docker container with
```sh
docker build  -t singlechoice-backend-dev .
```
And run it at port 8000 with 
```
docker run -d -p 8000:80 -e POSTGRES_URL="postgresql://host.docker.internal:5432/postgres" -e POSTGRES_USER="postgres" -e POSTGRES_PASSWORD="postgres" --name singlechoice-backend-dev singlechoice-backend-dev
```

To monitor, stop and remove the container you can use the following commands:
```sh
docker ps -a -f name=singlechoice-backend-dev
```
```sh
docker stop singlechoice-backend-dev
```
```sh
docker rm singlechoice-backend-dev
```

To run the prebuild container use
```sh
docker run -d -p 8000:80 -e POSTGRES_URL="postgresql://host.docker.internal:5432/postgres" -e POSTGRES_USER="postgres" -e POSTGRES_PASSWORD="postgres" --name singlechoice-backend ghcr.io/gamify-it/singlechoice-backend:latest
```


### testing database
to setup a database with docker for testing you can use
```sh
docker run -d -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres  --rm --name singlechoice-database postgres
```
To stop and remove it simply type
```sh
docker stop singlechoice-database
```

### Rest mappings
Rest mappings are defined in [`singlechoice-service/src/main/java/com/singlechoiceservice/singlechoiceservice/controller/SinglechoiceController.java`](singlechoice-service/src/main/java/com/singlechoiceservice/singlechoiceservice/controller/SinglechoiceController.java)