package com.erp.erp_layout.preferences.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class PreferencesDto {
    private String theme;
    private String language;
    private boolean sidebarCollapsed;
    private JsonNode modulePrefs;
}