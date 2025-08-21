package com.locquest.service;

import com.locquest.dto.UpLoadLocationRequest;
import com.locquest.entity.CategoryEntity;
import com.locquest.entity.LocationEntity;
import com.locquest.repository.CategoryRepository;
import com.locquest.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final S3Uploader s3Uploader;

    public LocationEntity createLocation(UpLoadLocationRequest request, String savedPath) {
        // 카테고리 조회
        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));

        // 위치 정보 저장
        LocationEntity location = new LocationEntity();
        location.setLocName(request.getLocName());
        location.setLocLat(request.getLatitude());
        location.setLocLng(request.getLongitude());
        location.setCategory(category);
        location.setLocImage(savedPath);
        location.setLocFailed(0);
        location.setLocSuccessed(0);

        return locationRepository.save(location);
    }

    public LocationEntity uploadLocation(UpLoadLocationRequest request, MultipartFile imageFile) {
        // 카테고리 조회
        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));

        // 이미지 업로드
        String imageUrl = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            imageUrl = s3Uploader.upload(imageFile);
        }

        // 위치 정보 저장
        LocationEntity location = new LocationEntity();
        location.setLocName(request.getLocName());
        location.setLocLat(request.getLatitude());
        location.setLocLng(request.getLongitude());
        location.setCategory(category);
        location.setLocImage(imageUrl);
        location.setLocFailed(0);
        location.setLocSuccessed(0);

        return locationRepository.save(location);
    }

    public List<LocationEntity> getLocationsByCategory(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));

        return locationRepository.findAll().stream()
                .filter(loc -> loc.getCategory().getCategoryId().equals(category.getCategoryId()))
                .toList();
    }

    public List<LocationEntity> getAllLocations() {
        return locationRepository.findAll();
    }

    public LocationEntity getLocationById(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("위치 정보를 찾을 수 없습니다."));
    }
}