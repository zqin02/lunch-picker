FROM openjdk:11-jdk-slim

WORKDIR /app
 
RUN apt-get update && apt-get install -y git
RUN git clone <REPO_URL> src

WORKDIR /app/src
 
RUN ./mvnw package

EXPOSE 8080
 
CMD ["java", "-jar", "target/lunch-0.0.1-SNAPSHOT.jar"]
