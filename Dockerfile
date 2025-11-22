
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY  /app/target/*.jar app.jar
# Run as non-root for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ENTRYPOINT ["java", "-jar", "app.jar"]