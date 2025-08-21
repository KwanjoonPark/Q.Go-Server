package com.locquest.controller;

import com.locquest.dto.UpLoadLocationRequest;
import com.locquest.entity.LocationEntity;
import com.locquest.service.LocationService;
import com.locquest.service.S3Uploader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Getter
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;
    private final S3Uploader s3Uploader;

    @PostMapping("/uploadLocation")
    public ResponseEntity<LocationEntity> uploadLocation(@ModelAttribute UpLoadLocationRequest request) throws IOException {
        String savedPath = s3Uploader.upload(request.getImage());
        LocationEntity location = locationService.createLocation(request, savedPath);

        return ResponseEntity.ok(location);
    }
}