FROM openjdk:21-jdk-slim
WORKDIR /app
COPY /target/hotel-0.0.1-SNAPSHOT.jar hotel.jar
ENTRYPOINT ["java", "-jar","hotel.jar"]