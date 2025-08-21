package com.locquest.controller;

import com.locquest.dto.ranking.getExplorerRankingResponse;
import com.locquest.dto.ranking.getTimeAttackRankingResponse;
import com.locquest.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/explorer")
    public ResponseEntity<getExplorerRankingResponse> getExplorerRanking(
            @RequestParam(defaultValue = "0") Long categoryId) {
        getExplorerRankingResponse response = rankingService.getExplorerRanking(categoryId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/timeattack")
    public ResponseEntity<getTimeAttackRankingResponse> getTimeAttackRanking(
            @RequestParam(defaultValue = "0") Long categoryId) {
        getTimeAttackRankingResponse response = rankingService.getTimeAttackRanking(categoryId);
        return ResponseEntity.ok(response);
    }
}