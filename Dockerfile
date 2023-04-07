FROM adoptopenjdk:11-jre-hotspot

ADD target/construction-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8083

ENTRYPOINT ["java","-jar","/app.jar"]