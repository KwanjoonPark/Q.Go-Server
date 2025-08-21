package com.locquest.controller;

import com.locquest.dto.*;
import com.locquest.entity.CategoryEntity;
import com.locquest.entity.GameEntity;
import com.locquest.entity.LocationEntity;
import com.locquest.repository.CategoryRepository;
import com.locquest.service.GameService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @GetMapping(value = "/getCategories", produces = "application/json; charset=UTF-8")
    public ResponseEntity<GetCategoryResponse> getCategories() {
        List<CategoryEntity> categoryList = gameService.getAllCategories();

        GetCategoryResponse response = new GetCategoryResponse();
        response.setCategoryList(categoryList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Content-Type", "application/json; charset=UTF-8");

        return ResponseEntity.ok()
                .headers(headers)
                .body(response);
    }

    @PostMapping(value = "/startGame", produces = "application/json; charset=UTF-8")
    public ResponseEntity<GameStartResponse> startGame(@RequestBody GameStartRequest request) {
        try {
            System.out.println("startGame 요청 받음: " + request);
            System.out.println("userId: " + request.getUserId());
            System.out.println("locCategory: " + request.getLocCategory());
            System.out.println("gameMode: " + request.getGameMode());

            // 게임 생성
            GameEntity savedGame = gameService.createGame(request);
            System.out.println("게임 생성 완료: " + savedGame.getGameId());

            // 랜덤으로 위치 사진 5개 뽑기
            List<LocationEntity> locations = gameService.getRandomLocationsByCategory(savedGame.getLocCategory().getCategoryId());
            System.out.println("위치 데이터 조회 완료: " + locations.size() + "개");

            GameStartResponse response = new GameStartResponse();
            response.setGameId(savedGame.getGameId());
            response.setLocCategory(savedGame.getLocCategory().getCategoryId());
            response.setLocationList(locations);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("startGame 에러: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("게임 생성에 실패했습니다: " + e.getMessage());
        }
    }

    @PostMapping("/sendSuccess")
    public ResponseEntity<Void> sendSuccess(@RequestBody SendSuccessRequest request) {
        gameService.recordComplete(request);
        gameService.countSuccess(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/endGame")
    public ResponseEntity<EndGameResponse> endGame(@RequestBody EndGameRequest request) {
        gameService.finishGame(request);
        gameService.failedLocations(request.getFailedLocations());
        Double elapsed = gameService.calculateTime(request);
        EndGameResponse resp = new EndGameResponse(elapsed);
        return ResponseEntity.ok(resp);
    }
}