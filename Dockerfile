


FROM eclipse-temurin:21-jre
LABEL authors="brkya"
EXPOSE 8080
COPY target/dtoPractice-0.0.1-SNAPSHOT.jar dtoPractice-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar","dtoPractice-0.0.1-SNAPSHOT.jar"]
