# Multi-stage build for Railway deployment v2
FROM amazoncorretto:21-alpine AS builder

WORKDIR /app

# Install required packages
RUN apk add --no-cache bash

# Gradle 래퍼와 소스 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# 실행 권한 부여 및 빌드 (테스트 제외)
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test --no-daemon --info

# Runtime stage
FROM amazoncorretto:21-alpine

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 포트 노출
EXPOSE 8080

# 애플리케이션 실행
CMD ["java", "-jar", "-Dspring.profiles.active=railway", "app.jar"]