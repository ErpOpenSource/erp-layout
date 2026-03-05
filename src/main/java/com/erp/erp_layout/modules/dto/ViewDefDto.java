package com.erp.erp_layout.modules.dto;

import lombok.Data;
import java.util.List;

@Data
public class ViewDefDto {
    private String id;
    private String label;
    private String type;
    private String endpoint;
    private List<ColumnDefDto> columns;
    private List<ActionDefDto> actions;
}