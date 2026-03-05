package com.erp.erp_layout.modules.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateModuleRequest(
        @NotBlank @Size(max = 50) String id,
        @NotBlank @Size(max = 100) String label,
        @NotBlank @Size(max = 100) String icon,
        @NotBlank @Size(max = 20) String color,
        int sortOrder
) {}
