package com.erp.erp_layout.modules.dto;

import lombok.Data;
import java.util.List;

@Data
public class DashboardDto {
    private List<WidgetDefDto> widgets;
}