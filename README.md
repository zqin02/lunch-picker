# README

## Introduction

This README provides instructions for setting up and running the lunch picker application using Docker Compose,
as well as setting up the development environment for both backend and frontend.

## Prerequisites

### Docker and Docker Compose Installation

1. **Install Docker**:
    - **Windows/Mac**: Download and install Docker Desktop from [Docker's official website](https://www.docker.com/products/docker-desktop).
    - **Linux**: Follow the instructions on [Docker's installation page](https://docs.docker.com/engine/install/).

2. **Install Docker Compose**:
    - Docker Compose is included with Docker Desktop for Windows/Mac. For Linux, follow the instructions on [Docker Compose installation page](https://docs.docker.com/compose/install/).

## Prerequisites for development

### Java Development Kit (JDK)

- **JDK 17**: Download from [AdoptOpenJDK](https://adoptium.net/) or [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).

### Node.js and Angular CLI

- **Node.js**: Download from [Node.js official website](https://nodejs.org/).
- **Angular CLI**: Install via npm (Node Package Manager):
  ```bash
  npm install -g @angular/cli
  ```


## Running the Application

### Docker Compose

1. **Clone the Repository**
2. **Configure Environment Variables**:
    - Update .env properties in /lunch/.env

3. **Start the Application**:
   ```bash
   docker-compose up
   ```
   This will start all services defined in the `docker-compose.yml` file.
   Please be aware that npm install take times to run.
4. **Start the Application in the background and leaves them running**:
   ```bash
   docker-compose up -d
   ```
   This will start all services defined in the `docker-compose.yml` file.

5. **Stop the Application**:
   ```bash
   docker-compose down
   ```
6. **Rebuild the Application**:
   ```bash
   docker-compose up -d --build [service]
   ```
   This will rebuild selected service services defined in the `docker-compose.yml` file.
---
## Development Setup

#### Backend Development

1. **Install JDK 17**:
    - Follow the installation instructions provided above.

2. **Setup IntelliJ IDEA**:
    - Open IntelliJ IDEA.
    - Import the Spring Boot project by selecting `File` -> `Open` and choosing the project directory.
    - Ensure that JDK 17 is selected for the project.

3. **Run the Application**:
    - Click on the green "Run" button or right-click on the main class containing the `@SpringBootApplication` annotation and select `Run`.

#### Frontend Development

1. **Install Node.js**:
    - Follow the installation instructions provided above.

2. **Install Angular CLI**:
   ```bash
   npm install -g @angular/cli
   ```

3. **Setup Angular Project**:
    - Navigate to the frontend project directory.
    - Install dependencies:
      ```bash
      npm install
      ```

4. **Run the Angular Application**:
   ```bash
   ng serve
   ```
   This will start the Angular development server and the application will be accessible at `http://localhost:4200`.

---

## Configuration Variables

Here is a list of variables in .env:

- **Ports**:
    - Modify the `docker-compose.yml` file to change the port mappings.
- **Environment Variables[docker]**:
    - relay.mq.host=rabbitmq
    - relay.mq.port=61613
    - relay.mq.pw=(replace it)
    - spring.datasource.url=jdbc:mysql://mysql:3306/db
    -  spring.datasource.username=(replace it)
    - spring.datasource.password=(replace it)
- **Environment Variables[development]**:
    - relay.mq.host=localhost
    - relay.mq.port=61613
    - relay.mq.pw=(replace it)
    - spring.datasource.url=jdbc:mysql://localhost:3309/db
    -  spring.datasource.username=(replace it)
    - spring.datasource.password=(replace it)
---

## Troubleshooting

- **Docker Issues**: Ensure Docker and Docker Compose are correctly installed and running. Check Docker logs for errors using:
  ```bash
  docker-compose logs
  ```
- **Git Issues**: Cannot build via docker compose up
    - [Window] failed to solve: process "/bin/sh -c ./mvnw package" did not complete successfully: exit code: 127
    - Fix with:
        ```bash
            git config core.autocrlf false
        ```

- **JDK Issues**: Ensure JDK 17 is properly installed and configured in IntelliJ IDEA.

- **Node.js Issues**: Verify Node.js and Angular CLI installations with:
  ```bash
  node -v
  npm -v
  ng version
  ```

