
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY  target/*.jar app.jar
COPY entrypoint.sh /entrypoint.sh

# Make script executable
RUN chmod +x /entrypoint.sh
# Run as non-root for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ENTRYPOINT ["/entrypoint.sh"]