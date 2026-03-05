package com.erp.erp_layout.modules.controller;

import com.erp.erp_layout.modules.dto.ApiResponse;
import com.erp.erp_layout.preferences.dto.PreferencesDto;
import com.erp.erp_layout.preferences.dto.UpdatePreferencesDto;
import com.erp.erp_layout.preferences.service.PreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/me")
@RequiredArgsConstructor
public class PreferencesController {

    private final PreferencesService service;

    // GET /api/users/me/preferences
    @GetMapping("/preferences")
    public ResponseEntity<ApiResponse<PreferencesDto>> getPreferences(
            Authentication auth) {

        String userId = auth.getName();
        return ResponseEntity.ok(ApiResponse.ok(service.getPreferences(userId)));
    }

    // PUT /api/users/me/preferences
    @PutMapping("/preferences")
    public ResponseEntity<ApiResponse<PreferencesDto>> updatePreferences(
            Authentication auth,
            @RequestBody UpdatePreferencesDto dto) {

        String userId = auth.getName();
        return ResponseEntity.ok(ApiResponse.ok(service.updatePreferences(userId, dto)));
    }
}