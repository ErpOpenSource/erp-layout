package com.erp.erp_layout.modules.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateFormRequest(
        @NotBlank @Size(max = 100) String label,
        @NotBlank @Size(max = 255) String endpoint,
        @NotBlank @Size(max = 10) String method
) {}
