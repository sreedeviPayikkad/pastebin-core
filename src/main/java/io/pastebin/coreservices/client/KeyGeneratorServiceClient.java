package io.pastebin.coreservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "KeyGeneratorServiceClient", url = "http://localhost:8081")
public interface KeyGeneratorServiceClient {
    @PostMapping(path = "/api/v1/generate", consumes = "application/json")
    ResponseEntity<Object> getGeneratedKey(String clientIP);
}
