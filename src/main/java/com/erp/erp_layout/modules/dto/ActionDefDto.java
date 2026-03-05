package com.erp.erp_layout.modules.dto;

import lombok.Data;

@Data
public class ActionDefDto {
    private String type;
    private String label;
    private String icon;
    private String permission;
    private boolean confirm;
    private String form;
}