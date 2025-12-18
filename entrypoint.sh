#!/bin/sh
set -e  # Exit on error

export FRONTEND_URL=$(aws ssm get-parameter --name "/prod/FRONTEND_URL" --query "Parameter.Value" --output text)

export DB_NAME=$(aws ssm get-parameter --name "/prod/DB_NAME" --query "Parameter.Value" --output text)

export DB_HOST=$(aws ssm get-parameter --name "/prod/DB_HOST" --query "Parameter.Value" --output text)

export DB_USER=$(aws ssm get-parameter --name "/prod/DB_USER" --query "Parameter.Value" --output text)

export DB_PASS=$(aws ssm get-parameter --name "/prod/DB_PASS" --query "Parameter.Value" --output text)

# Run the Spring Boot app
exec java -jar /app/app.jar