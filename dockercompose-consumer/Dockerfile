FROM openjdk:8
COPY target/dockercompose-consumer-0.0.1-SNAPSHOT.jar /tmp/dockercompose-consumer-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/tmp/dockercompose-consumer-0.0.1-SNAPSHOT.jar"]