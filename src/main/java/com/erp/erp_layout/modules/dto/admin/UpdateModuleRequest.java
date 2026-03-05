package com.erp.erp_layout.modules.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateModuleRequest(
        @NotBlank @Size(max = 100) String label,
        @NotBlank @Size(max = 100) String icon,
        @NotBlank @Size(max = 20) String color,
        Boolean enabled,
        Integer sortOrder
) {}
