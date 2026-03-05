package com.erp.erp_layout.modules.dto;

import lombok.Data;

@Data
public class WidgetDefDto {
    private String id;
    private String label;
    private String type;
    private String endpoint;
    private String icon;
    private String color;
    private int span;
}