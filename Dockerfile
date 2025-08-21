FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Gradle wrapper와 소스 코드 복사
COPY . .

# Gradle 설치 및 빌드
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.5-bin.zip && \
    unzip gradle-8.5-bin.zip && \
    mv gradle-8.5 /opt/gradle && \
    rm gradle-8.5-bin.zip

ENV PATH="/opt/gradle/bin:${PATH}"

# Gradle 빌드 실행
RUN gradle build -x test

# 런타임 이미지
FROM eclipse-temurin:21-jre

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 포트 노출
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=railway", "app.jar"]