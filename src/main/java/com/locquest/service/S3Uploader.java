package com.locquest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Uploader {

    @Value("${cloud.aws.credentials.access-key:}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key:}")
    private String secretKey;
    @Value("${cloud.aws.region.static:ap-northeast-2}")
    private String region;
    @Value("${cloud.aws.s3.bucket:}")
    private String bucket;

    public String upload(MultipartFile file) {
        try {
            // S3 자격증명 확인
            if (accessKey == null || accessKey.trim().isEmpty() || "placeholder".equals(accessKey)) {
                System.out.println("S3 자격증명이 설정되지 않았습니다. 파일 업로드를 건너뜁니다.");
                return "https://placeholder.s3.amazonaws.com/" + file.getOriginalFilename();
            }
            
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;

            S3Client s3 = S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(accessKey, secretKey)))
                    .build();

            PutObjectRequest putReq = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(uniqueFilename)
                    .contentType(file.getContentType())
                    .build();

            System.out.println("Uploading to S3:");
            System.out.println("  Bucket: " + bucket);
            System.out.println("  Region: " + region);
            System.out.println("  Filename: " + uniqueFilename);
            System.out.println("  ContentType: " + file.getContentType());
            System.out.println("  Size: " + file.getSize());

            RequestBody requestBody = RequestBody.fromInputStream(file.getInputStream(), file.getSize());
            s3.putObject(putReq, requestBody);

            return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + uniqueFilename;

        } catch (IOException e) {
            throw new RuntimeException("S3 업로드 실패", e);
        }
    }
}
