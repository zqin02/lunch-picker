 
FROM openjdk:17-jdk-slim
 
WORKDIR /app
COPY . /app
RUN chmod +x ./mvnw
RUN ./mvnw package
EXPOSE 8080
 
ENTRYPOINT ["java", "-jar", "target/lunch-0.0.1-SNAPSHOT.jar"]
