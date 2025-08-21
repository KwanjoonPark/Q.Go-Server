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
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow();
        CategoryEntity category = categoryRepository.findById(request.getLocCategory()).orElseThrow();

        GameEntity game = GameEntity.builder()
                .user(user)
                .gameMode(request.getGameMode())
                .gameDate(request.getGameDate())
                .startTime(request.getStartTime())
                .locCategory(category)
                .build();

        return gameRepository.save(game);
    }

    public List<LocationEntity> getRandomLocationsByCategory(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("카테고리 없음"));

        List<LocationEntity> allLocations = locationRepository.findByCategory(category);

        Collections.shuffle(allLocations); // ✅ 섞고
        return allLocations.stream()
                .limit(5) // ✅ 앞에서 5개만 추출
                .toList();
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