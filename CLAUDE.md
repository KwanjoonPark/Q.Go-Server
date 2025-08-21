# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

LocQuest is a Spring Boot 3 location-based game API built with Java 17. The application uses MySQL for data persistence, AWS S3 for file storage, and Kakao OAuth for authentication. It features JWT-based security, location tracking, game management, and ranking systems.

## Architecture

### Core Components
- **Controllers**: REST API endpoints in `src/main/java/com/locquest/controller/`
  - `AuthController`: Kakao OAuth authentication
  - `GameController`: Game session management (start/end game, category selection)
  - `LocationController`: Location upload and management
  - `RankingController`: Explorer and Time Attack rankings
- **Services**: Business logic layer
  - `AuthService`/`KakaoOAuthService`: OAuth integration and user management
  - `GameService`: Game state and scoring logic
  - `LocationService`: Location data processing
  - `RankingService`: Ranking calculations and queries
  - `S3Uploader`: AWS S3 integration for file uploads
- **Entities**: JPA entities representing core data models
  - `UserEntity`: User profiles linked to Kakao accounts
  - `GameEntity`: Game sessions with modes (Explorer/Time Attack)
  - `LocationEntity`: Geographic points with categories
  - `CategoryEntity`: Location categorization
  - `CompleteEntity`: User game completion tracking
- **Security**: JWT-based authentication
  - `JwtAuthenticationFilter`: Request filtering
  - `JwtTokenProvider`/`JwtUtil`: Token management
  - `SecurityConfig`: Spring Security configuration

### Game Modes
- **Explorer Mode**: Discovery-based gameplay with completion tracking
- **Time Attack Mode**: Speed-based challenges with time scoring

## Development Commands

### Build and Run
```bash
# Build the project
./gradlew build

# Run locally (requires MySQL)
./gradlew bootRun

# Run tests
./gradlew test

# Clean build artifacts
./gradlew clean
```

### Database Setup
Requires MySQL database named `locquest`. Configure connection in `src/main/resources/application.properties`:
- URL: `jdbc:mysql://localhost:3306/locquest`
- Default username: `root`
- JPA auto-DDL enabled for development

### Deployment
- **Railway**: Uses `railway.toml` configuration
- **Docker**: Pre-built JAR approach with Amazon Corretto 21
- **Build Command**: `./gradlew build` produces JAR in `build/libs/`
- **Start Command**: `java -Dserver.port=$PORT -Dspring.profiles.active=railway -jar app.jar`

## Configuration

### Environment Profiles
- **Local**: `application.properties` (default)
- **Railway**: `application-railway.properties` (production)

### External Dependencies
- **Kakao OAuth**: Client ID and redirect URI configured
- **AWS S3**: Bucket `locquest-bucket` in `ap-northeast-2` region
- **JWT**: Uses custom secret key for token signing

### Key Configuration Files
- `build.gradle`: Dependencies and build configuration
- `src/main/resources/application*.properties`: Environment-specific settings
- `src/main/java/com/locquest/config/`: Spring configuration classes

## Data Flow
1. Users authenticate via Kakao OAuth (`AuthController`)
2. Games are started with category selection (`GameController`)
3. Locations are uploaded with photos to S3 (`LocationController`)
4. Game completion triggers scoring and ranking updates (`GameService`, `RankingService`)
5. Rankings are queried using custom projections (`RankingController`)