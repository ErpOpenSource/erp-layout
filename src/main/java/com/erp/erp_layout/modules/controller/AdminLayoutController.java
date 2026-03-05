package com.erp.erp_layout.modules.controller;

import com.erp.erp_layout.modules.dto.ApiResponse;
import com.erp.erp_layout.modules.dto.ModuleSchemaDto;
import com.erp.erp_layout.modules.dto.admin.CreateFormRequest;
import com.erp.erp_layout.modules.dto.admin.CreateModuleRequest;
import com.erp.erp_layout.modules.dto.admin.CreateNavItemRequest;
import com.erp.erp_layout.modules.dto.admin.CreateViewRequest;
import com.erp.erp_layout.modules.dto.admin.UpdateFormRequest;
import com.erp.erp_layout.modules.dto.admin.UpdateModuleRequest;
import com.erp.erp_layout.modules.dto.admin.UpdateNavItemRequest;
import com.erp.erp_layout.modules.dto.admin.UpdateViewRequest;
import com.erp.erp_layout.modules.service.AdminLayoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminLayoutController {

    private final AdminLayoutService service;

    // ── Modules ───────────────────────────────────────────────────────────────

    @GetMapping("/modules")
    public ResponseEntity<ApiResponse<List<ModuleSchemaDto>>> listModules() {
        return ResponseEntity.ok(ApiResponse.ok(service.listModules()));
    }

    @PostMapping("/modules")
    public ResponseEntity<ApiResponse<ModuleSchemaDto>> createModule(@Valid @RequestBody CreateModuleRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.createModule(req)));
    }

    @PutMapping("/modules/{moduleId}")
    public ResponseEntity<ApiResponse<ModuleSchemaDto>> updateModule(
            @PathVariable String moduleId,
            @Valid @RequestBody UpdateModuleRequest req
    ) {
        return ResponseEntity.ok(ApiResponse.ok(service.updateModule(moduleId, req)));
    }

    @DeleteMapping("/modules/{moduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteModule(@PathVariable String moduleId) {
        service.deleteModule(moduleId);
    }

    // ── Nav items ─────────────────────────────────────────────────────────────

    @GetMapping("/modules/{moduleId}/nav-items")
    public ResponseEntity<ApiResponse<?>> listNavItems(@PathVariable String moduleId) {
        return ResponseEntity.ok(ApiResponse.ok(service.listNavItems(moduleId)));
    }

    @PostMapping("/modules/{moduleId}/nav-items")
    public ResponseEntity<ApiResponse<?>> createNavItem(
            @PathVariable String moduleId,
            @Valid @RequestBody CreateNavItemRequest req
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.createNavItem(moduleId, req)));
    }

    @PutMapping("/modules/{moduleId}/nav-items/{navItemId}")
    public ResponseEntity<ApiResponse<?>> updateNavItem(
            @PathVariable String moduleId,
            @PathVariable String navItemId,
            @Valid @RequestBody UpdateNavItemRequest req
    ) {
        return ResponseEntity.ok(ApiResponse.ok(service.updateNavItem(moduleId, navItemId, req)));
    }

    @DeleteMapping("/modules/{moduleId}/nav-items/{navItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNavItem(@PathVariable String moduleId, @PathVariable String navItemId) {
        service.deleteNavItem(moduleId, navItemId);
    }

    // ── Views ─────────────────────────────────────────────────────────────────

    @GetMapping("/modules/{moduleId}/views")
    public ResponseEntity<ApiResponse<?>> listViews(@PathVariable String moduleId) {
        return ResponseEntity.ok(ApiResponse.ok(service.listViews(moduleId)));
    }

    @PostMapping("/modules/{moduleId}/views")
    public ResponseEntity<ApiResponse<?>> createView(
            @PathVariable String moduleId,
            @Valid @RequestBody CreateViewRequest req
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.createView(moduleId, req)));
    }

    @PutMapping("/modules/{moduleId}/views/{viewId}")
    public ResponseEntity<ApiResponse<?>> updateView(
            @PathVariable String moduleId,
            @PathVariable String viewId,
            @Valid @RequestBody UpdateViewRequest req
    ) {
        return ResponseEntity.ok(ApiResponse.ok(service.updateView(moduleId, viewId, req)));
    }

    @DeleteMapping("/modules/{moduleId}/views/{viewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteView(@PathVariable String moduleId, @PathVariable String viewId) {
        service.deleteView(moduleId, viewId);
    }

    // ── Forms ─────────────────────────────────────────────────────────────────

    @GetMapping("/modules/{moduleId}/forms")
    public ResponseEntity<ApiResponse<?>> listForms(@PathVariable String moduleId) {
        return ResponseEntity.ok(ApiResponse.ok(service.listForms(moduleId)));
    }

    @PostMapping("/modules/{moduleId}/forms")
    public ResponseEntity<ApiResponse<?>> createForm(
            @PathVariable String moduleId,
            @Valid @RequestBody CreateFormRequest req
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.createForm(moduleId, req)));
    }

    @PutMapping("/modules/{moduleId}/forms/{formId}")
    public ResponseEntity<ApiResponse<?>> updateForm(
            @PathVariable String moduleId,
            @PathVariable String formId,
            @Valid @RequestBody UpdateFormRequest req
    ) {
        return ResponseEntity.ok(ApiResponse.ok(service.updateForm(moduleId, formId, req)));
    }

    @DeleteMapping("/modules/{moduleId}/forms/{formId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteForm(@PathVariable String moduleId, @PathVariable String formId) {
        service.deleteForm(moduleId, formId);
    }
}
