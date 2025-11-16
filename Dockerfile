FROM openjdk:21-rc-jdk
WORKDIR /app
EXPOSE 8080
COPY target/aston_module_2-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]