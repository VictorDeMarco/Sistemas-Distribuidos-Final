FROM azul/zulu-openjdk:21
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY target/books-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar","app.jar"]