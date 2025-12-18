
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY  target/*.jar app.jar
COPY entrypoint.sh /entrypoint.sh

RUN apk update && \
    apk add --no-cache aws-cli && \
    rm -rf /var/cache/apk/* && \
    chmod +x /entrypoint.sh
# Run as non-root for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ENTRYPOINT ["/entrypoint.sh"]