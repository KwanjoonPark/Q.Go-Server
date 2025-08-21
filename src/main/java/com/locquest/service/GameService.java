package com.locquest.service;

import com.locquest.dto.*;
import com.locquest.entity.*;
import com.locquest.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final CompleteRepository completeRepository;

    public GameEntity createGame(GameStartRequest request) {
        try {
            System.out.println("사용자 조회 시작: " + request.getUserId());
            UserEntity user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + request.getUserId()));
            System.out.println("사용자 조회 완료: " + user.getNickname());

            System.out.println("카테고리 조회 시작: " + request.getLocCategory());
            CategoryEntity category = categoryRepository.findById(request.getLocCategory())
                    .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다: " + request.getLocCategory()));
            System.out.println("카테고리 조회 완료: " + category.getCategoryName());

            GameEntity game = GameEntity.builder()
                    .user(user)
                    .gameMode(request.getGameMode())
                    .gameDate(request.getGameDate())
                    .startTime(request.getStartTime())
                    .locCategory(category)
                    .build();

            System.out.println("게임 엔티티 저장 시작");
            GameEntity savedGame = gameRepository.save(game);
            System.out.println("게임 엔티티 저장 완료: " + savedGame.getGameId());
            
            return savedGame;
        } catch (Exception e) {
            System.err.println("createGame 에러: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<LocationEntity> getRandomLocationsByCategory(Long categoryId) {
        try {
            System.out.println("카테고리별 위치 조회 시작: " + categoryId);
            CategoryEntity category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("카테고리 없음: " + categoryId));

            List<LocationEntity> allLocations = locationRepository.findByCategory(category);
            System.out.println("해당 카테고리의 전체 위치 수: " + allLocations.size());

            if (allLocations.isEmpty()) {
                throw new RuntimeException("해당 카테고리에 위치 데이터가 없습니다: " + categoryId);
            }

            Collections.shuffle(allLocations); // ✅ 섞고
            List<LocationEntity> result = allLocations.stream()
                    .limit(5) // ✅ 앞에서 5개만 추출
                    .toList();
            
            System.out.println("랜덤 선택된 위치 수: " + result.size());
            return result;
        } catch (Exception e) {
            System.err.println("getRandomLocationsByCategory 에러: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public CompleteEntity recordComplete(SendSuccessRequest request) {
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow();
        LocationEntity location = locationRepository.findById(request.getLocId()).orElseThrow();
        GameEntity game = gameRepository.findById(request.getGameId()).orElseThrow();

        CompleteEntity complete = CompleteEntity.builder()
                .user(user)
                .location(location)
                .game(game)
                .completeDate(request.getCompleteDate())
                .build();

        return completeRepository.save(complete);
    }

    public LocationEntity countSuccess(SendSuccessRequest request) {
        LocationEntity location = locationRepository.findById(request.getLocId()).orElseThrow();
        location.setLocSuccessed(location.getLocSuccessed() + 1);
        return locationRepository.save(location);
    }

    public GameEntity finishGame(EndGameRequest request) {
        GameEntity game = gameRepository.findById(request.getGameId()).orElseThrow();
        game.setHintCount(request.getHintCount());
        game.setLocCount(request.getLocCount());
        game.setSuccess(request.getSuccess());
        game.setEndTime(request.getEndTime());
        return gameRepository.save(game);
    }

    public List<LocationEntity> failedLocations(List<Long> locationList) {
        List<LocationEntity> updatedList = new ArrayList<>();
        for (Long failedLocation : locationList) {
            LocationEntity location = locationRepository.findById(failedLocation).orElseThrow();
            location.setLocFailed(location.getLocFailed() + 1);
            LocationEntity saved = locationRepository.save(location);
            updatedList.add(saved);
        }

        return updatedList;
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Double calculateTime(EndGameRequest request) {
        GameEntity game = gameRepository.findById(request.getGameId()).orElseThrow();
        LocalDateTime startTime = game.getStartTime();
        LocalDateTime endTime = request.getEndTime();
        Duration dur = Duration.between(startTime, endTime);
        return dur.toMillis() / 1_000.0;
    }
}