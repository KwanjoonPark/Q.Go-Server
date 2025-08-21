package com.locquest.controller;

import com.locquest.dto.UpLoadLocationRequest;
import com.locquest.entity.LocationEntity;
import com.locquest.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/upload")
    public ResponseEntity<LocationEntity> uploadLocation(
            @RequestPart("locationData") UpLoadLocationRequest request,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {
        
        LocationEntity location = locationService.uploadLocation(request, imageFile);
        return ResponseEntity.ok(location);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<LocationEntity>> getLocationsByCategory(@PathVariable Long categoryId) {
        List<LocationEntity> locations = locationService.getLocationsByCategory(categoryId);
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocationEntity>> getAllLocations() {
        List<LocationEntity> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationEntity> getLocationById(@PathVariable Long locationId) {
        LocationEntity location = locationService.getLocationById(locationId);
        return ResponseEntity.ok(location);
    }
}