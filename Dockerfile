# Stage 1: Build the Spring Boot application
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# Copy Maven wrapper and config
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy pom.xml
COPY pom.xml .

# Download dependencies (cached)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the application
RUN ./mvnw -DskipTests package

# Stage 2: Run the application
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built jar
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
