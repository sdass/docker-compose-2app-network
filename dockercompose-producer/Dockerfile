FROM openjdk:8

ADD target/dockercompose-producer-0.0.1-SNAPSHOT.jar /tmp/dockercompose-producer-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/tmp/dockercompose-producer-0.0.1-SNAPSHOT.jar"]