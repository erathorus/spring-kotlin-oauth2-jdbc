# Spring Boot 2 with Kotlin and Spring Security OAuth2
This project provides a production ready example of Spring Boot 2 and Spring Security OAuth2.

## Quick start
First, you have to create a postgresql docker image:
~~~
docker run --name milky-way -p 5432:5432 -e POSTGRES_DB=milky-way -e POSTGRES_PASSWORD=milkyway -d postgres
~~~

To run the project using Spring Boot:
~~~
./gradlew bootRun # On Unix
.\gradlew.bat bootRun # On Windows
~~~

Spring Boot will run on port `8080`. In order to request a new token:
~~~
curl -X POST --user 'my-client:secret' -d 'grant_type=password&username=user1&password=abcd1234' http://localhost:8080/oauth/token
~~~

To request resource:
~~~
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" -X GET http://localhost:8080/hello
~~~

## License
The code is released under the Apache License 2.0
