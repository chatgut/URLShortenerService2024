FROM eclipse-temurin:22-jre-alpine

WORKDIR /app

COPY target/*.jar app.jar


RUN apk --no-cache add mysql-client

CMD ["java", "-jar", "app.jar"]