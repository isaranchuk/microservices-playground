FROM openjdk:10-jre-slim

COPY target/*.jar /app.jar

VOLUME /tmp

EXPOSE 9090

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-Xms256m", "-Xms256m", "-jar", "app.jar"]
