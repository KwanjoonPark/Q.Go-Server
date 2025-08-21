package com.locquest.service;

import com.locquest.dto.ranking.*;
import com.locquest.repository.GameRepository;
import com.locquest.repository.projection.ExplorerRankingProjection;
import com.locquest.repository.projection.TimeAttackRankingProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final GameRepository gameRepository;

    public getExplorerRankingResponse getExplorerRanking(Long categoryId) {
        List<ExplorerRankingProjection> projections = gameRepository.getExplorerRankingByCategory(categoryId);
        
        List<ExplorerRankEntry> rankings = projections.stream()
                .map(projection -> {
                    return new ExplorerRankEntry(
                            String.valueOf(projection.getUserId()),
                            projection.getUserName(),
                            projection.getTotalLocCount(),
                            projection.getTotalHintUsed()
                    );
                })
                .collect(Collectors.toList());

        getExplorerRankingResponse response = new getExplorerRankingResponse();
        response.setExplorerRankingList(rankings);
        return response;
    }

    public getTimeAttackRankingResponse getTimeAttackRanking(Long categoryId) {
        List<TimeAttackRankingProjection> projections = gameRepository.getTimeAttackRankingByCategory(categoryId);
        
        List<TimeAttackRankEntry> rankings = projections.stream()
                .map(projection -> {
                    return new TimeAttackRankEntry(
                            String.valueOf(projection.getUserId()),
                            projection.getUserName(),
                            projection.getTotalTime()
                    );
                })
                .collect(Collectors.toList());

        getTimeAttackRankingResponse response = new getTimeAttackRankingResponse();
        response.setTimeAttackRanking(rankings);
        return response;
    }
}