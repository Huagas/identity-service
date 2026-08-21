# Stage 1: build
# Start with a Maven image that includes JDK 21
FROM maven:3.9.16-eclipse-temurin-21-noble AS build

# Copy source code and pom.xml file to /app folder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build source code with maven
RUN mvn -B clean package -DskipTests

# Stage 2: create image

FROM eclipse-temurin:21-jre-noble

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]