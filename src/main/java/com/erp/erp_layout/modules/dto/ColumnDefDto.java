package com.erp.erp_layout.modules.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class ColumnDefDto {
    private String field;
    private String label;
    private String type;
    private boolean sortable;
    private boolean filterable;
    private Integer width;
    private JsonNode badgeOptions;
}