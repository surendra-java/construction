FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app
COPY target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]