package com.erp.erp_layout.version;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.erp_layout.modules.dto.ApiResponse;

import java.util.Map;

@RestController
@RequestMapping("/api/app")
public class VersionController {

    @Value("${app.version:1.0.0}")
    private String version;

    @GetMapping("/version")
    public ResponseEntity<ApiResponse<Map<String, String>>> getVersion() {
        return ResponseEntity.ok(ApiResponse.ok(Map.of("version", version)));
    }
}