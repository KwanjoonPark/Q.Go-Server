package com.locquest.service;

import com.locquest.dto.*;
import com.locquest.entity.*;
import com.locquest.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    public GameStartResponse startGame(GameStartRequest request) {
        // 사용자 조회
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 카테고리 조회
        CategoryEntity category = categoryRepository.findById(request.getLocCategory())
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));

        // 게임 생성
        GameEntity game = GameEntity.builder()
                .user(user)
                .gameMode(request.getGameMode())
                .startTime(request.getStartTime())
                .gameDate(request.getGameDate())
                .locCategory(category)
                .success(false)
                .locCount(0)
                .hintCount(0)
                .build();

        GameEntity savedGame = gameRepository.save(game);

        // 해당 카테고리의 위치 목록 조회
        List<LocationEntity> locations = locationRepository.findAll().stream()
                .filter(loc -> loc.getCategory().getCategoryId().equals(category.getCategoryId()))
                .toList();

        // 응답 생성
        GameStartResponse response = new GameStartResponse();
        response.setGameId(savedGame.getGameId());
        response.setLocCategory(request.getLocCategory());
        response.setLocationList(locations);

        return response;
    }

    public EndGameResponse endGame(EndGameRequest request) {
        // 게임 조회
        GameEntity game = gameRepository.findById(request.getGameId())
                .orElseThrow(() -> new RuntimeException("게임을 찾을 수 없습니다."));

        // 게임 결과 업데이트
        game.setEndTime(request.getEndTime());
        game.setSuccess(request.getSuccess());
        game.setLocCount(request.getLocCount());
        game.setHintCount(request.getHintCount());

        gameRepository.save(game);

        // 경과 시간 계산 (초 단위)
        long elapsedSeconds = java.time.Duration.between(game.getStartTime(), request.getEndTime()).getSeconds();
        
        // 응답 생성
        EndGameResponse response = new EndGameResponse((double) elapsedSeconds);

        return response;
    }

    // 사용자의 게임 목록 조회
    public List<GameEntity> getUserGames(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        
        return gameRepository.findAll().stream()
                .filter(game -> game.getUser().getUserId().equals(userId))
                .toList();
    }

    // 카테고리 목록 조회
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 게임 생성 (GameController용)
    public GameEntity createGame(GameStartRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        CategoryEntity category = categoryRepository.findById(request.getLocCategory())
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));

        GameEntity game = GameEntity.builder()
                .user(user)
                .gameMode(request.getGameMode())
                .startTime(request.getStartTime())
                .gameDate(request.getGameDate())
                .locCategory(category)
                .success(false)
                .locCount(0)
                .hintCount(0)
                .build();

        return gameRepository.save(game);
    }

    // 카테고리별 랜덤 위치 조회
    public List<LocationEntity> getRandomLocationsByCategory(Long categoryId) {
        return locationRepository.findAll().stream()
                .filter(loc -> loc.getCategory().getCategoryId().equals(categoryId))
                .limit(5)
                .toList();
    }

    // 완료 기록
    public void recordComplete(SendSuccessRequest request) {
        // TODO: CompleteEntity 저장 로직 구현
    }

    // 성공 카운트
    public void countSuccess(SendSuccessRequest request) {
        // TODO: 성공 카운트 증가 로직 구현
    }

    // 게임 종료
    public void finishGame(EndGameRequest request) {
        GameEntity game = gameRepository.findById(request.getGameId())
                .orElseThrow(() -> new RuntimeException("게임을 찾을 수 없습니다."));
        game.setSuccess(true);
        game.setEndTime(LocalDateTime.now());
        gameRepository.save(game);
    }

    // 실패 위치 처리
    public void failedLocations(List<Long> failedLocationIds) {
        // TODO: 실패 위치 처리 로직 구현
    }

    // 시간 계산
    public Double calculateTime(EndGameRequest request) {
        GameEntity game = gameRepository.findById(request.getGameId())
                .orElseThrow(() -> new RuntimeException("게임을 찾을 수 없습니다."));
        
        if (game.getStartTime() != null && game.getEndTime() != null) {
            return (double) java.time.Duration.between(game.getStartTime(), game.getEndTime()).getSeconds();
        }
        return 0.0;
    }
}