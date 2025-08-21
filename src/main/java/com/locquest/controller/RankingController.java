package com.locquest.controller;

import com.locquest.dto.ranking.ExplorerRankEntry;
import com.locquest.dto.ranking.TimeAttackRankEntry;
import com.locquest.dto.ranking.getExplorerRankingResponse;
import com.locquest.dto.ranking.getTimeAttackRankingResponse;
import com.locquest.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ranking")
public class RankingController {

    private final RankingService rankingService;

    @GetMapping(value = "/explorer", produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<ExplorerRankEntry>> getExplorerRanking(
            @RequestParam Long categoryId
    ) {
        System.out.println("explorer ranking response 실행");
        List<ExplorerRankEntry> result = rankingService.getExplorerRanking(categoryId);
        System.out.println("explorer ranking 결과: " + result);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/timeAttack", produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<TimeAttackRankEntry>> getTimeAttackRanking(
            @RequestParam Long categoryId
    ) {
        System.out.println("timeAttack response 실행");
        List<TimeAttackRankEntry> result = rankingService.getTimeAttackRanking(categoryId);
        System.out.println("timeAttack ranking 결과: " + result);
        return ResponseEntity.ok(result);
    }
}