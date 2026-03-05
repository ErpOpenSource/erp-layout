package com.erp.erp_layout.modules.dto;

import lombok.Data;
import java.util.List;

@Data
public class ModuleSchemaDto {
    private String id;
    private String label;
    private String icon;
    private String color;
    private boolean enabled;
    private int sortOrder;
    private List<NavItemDto> navItems;
    private List<ViewDefDto> views;
    private List<FormDefDto> forms;
    private DashboardDto dashboard;
}