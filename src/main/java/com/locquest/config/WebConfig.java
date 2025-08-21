package com.locquest.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // UTF-8 인코딩 설정
        converters.stream()
                .filter(StringHttpMessageConverter.class::isInstance)
                .map(StringHttpMessageConverter.class::cast)
                .forEach(conv -> conv.setDefaultCharset(StandardCharsets.UTF_8));
        
        // Jackson 설정
        converters.stream()
                .filter(MappingJackson2HttpMessageConverter.class::isInstance)
                .map(MappingJackson2HttpMessageConverter.class::cast)
                .forEach(conv -> {
                    var mapper = conv.getObjectMapper();
                    // LocalDateTime 모듈 등록
                    mapper.registerModule(new JavaTimeModule());
                    // 타임스탬프 대신 ISO-8601 문자열
                    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                });
    }
}

