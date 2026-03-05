package com.erp.erp_layout.modules.dto;

import lombok.Data;
import java.util.List;

@Data
public class FormDefDto {
    private String id;
    private String label;
    private String endpoint;
    private String method;
    private List<FormFieldDto> fields;
}