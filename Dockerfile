# 빌드된 JAR을 직접 사용하는 간단한 Dockerfile
FROM amazoncorretto:21-alpine

WORKDIR /app

# 미리 빌드된 JAR 파일 복사
COPY build/libs/*.jar app.jar

# 포트 노출
EXPOSE 8080

# 애플리케이션 실행
CMD ["java", "-jar", "-Dspring.profiles.active=railway", "app.jar"]