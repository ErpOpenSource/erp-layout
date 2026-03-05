package com.erp.erp_layout.modules.dto;

import lombok.Data;
import java.util.List;

@Data
public class FormFieldDto {
    private String name;
    private String label;
    private String type;
    private boolean required;
    private String placeholder;
    private String defaultValue;
    private String source;
    private List<FormFieldDto> fields; // para repeater
}