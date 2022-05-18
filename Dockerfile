FROM openjdk:11
COPY build/libs/demo-0.0.1-SNAPSHOT.jar hotel.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "hotel.jar"]
