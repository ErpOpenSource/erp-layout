package com.erp.erp_layout.preferences.mapper;

import com.erp.erp_layout.modules.domain.UserPreferences;
import com.erp.erp_layout.preferences.dto.PreferencesDto;
import com.erp.erp_layout.preferences.dto.UpdatePreferencesDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PreferencesMapper {

    PreferencesDto toDto(UserPreferences prefs);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateFromDto(UpdatePreferencesDto dto, @MappingTarget UserPreferences prefs);
}