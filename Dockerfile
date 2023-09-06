#
# Build stage
#
FROM openjdk:17.0.1-jdk-slim AS build

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/fourniture-0.0.1-SNAPSHOT.jar fourniture.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","fourniture.jar"]