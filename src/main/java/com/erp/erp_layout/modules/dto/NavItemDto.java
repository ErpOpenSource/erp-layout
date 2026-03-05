package com.erp.erp_layout.modules.dto;

import lombok.Data;

@Data
public class NavItemDto {
    private String id;
    private String label;
    private String icon;
    private String path;
    private String permission;
    private Integer badge;
}