package com.erp.erp_layout.modules.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateViewRequest(
        @NotBlank @Size(max = 50) String id,
        @NotBlank @Size(max = 100) String label,
        @NotBlank @Size(max = 50) String type,
        @NotBlank @Size(max = 255) String endpoint,
        int sortOrder
) {}
