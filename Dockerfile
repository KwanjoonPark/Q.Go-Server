FROM gradle:8.5-jdk21 AS build

WORKDIR /app

# Gradle wrapper와 소스 코드 복사
COPY . .

# Gradle 빌드 실행 (gradle 명령어 직접 사용)
RUN gradle build -x test

# 런타임 이미지
FROM openjdk:21-jre-slim

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 포트 노출
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=railway", "app.jar"]