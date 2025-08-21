FROM gradle:8.5-jdk21 AS build

WORKDIR /app

# Gradle wrapper와 소스 코드 복사
COPY . .

# 실행 권한 부여 후 빌드
RUN chmod +x ./gradlew && ./gradlew build -x test

# 런타임 이미지
FROM openjdk:21-jre-slim

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 포트 노출
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=railway", "app.jar"]