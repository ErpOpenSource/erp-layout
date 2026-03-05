package com.erp.erp_layout.modules.controller;

import com.erp.erp_layout.modules.dto.ApiResponse;
import com.erp.erp_layout.modules.dto.ModuleSchemaDto;
import com.erp.erp_layout.modules.service.ModuleSchemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleSchemaController {

    private final ModuleSchemaService service;

    // GET /api/modules/SALES/schema
    @GetMapping("/{moduleId}/schema")
    public ResponseEntity<ApiResponse<ModuleSchemaDto>> getSchema(
            @PathVariable String moduleId) {

        ModuleSchemaDto schema = service.getSchema(moduleId);
        return ResponseEntity.ok(ApiResponse.ok(schema));
    }

    // GET /api/modules?ids=SALES,DASHBOARD
    @GetMapping
    public ResponseEntity<ApiResponse<List<ModuleSchemaDto>>> getModules(
            @RequestParam(required = false) List<String> ids) {

        List<ModuleSchemaDto> modules = ids != null && !ids.isEmpty()
                ? service.getSchemasByIds(ids)
                : service.getAllEnabled();

        return ResponseEntity.ok(ApiResponse.ok(modules));
    }
}