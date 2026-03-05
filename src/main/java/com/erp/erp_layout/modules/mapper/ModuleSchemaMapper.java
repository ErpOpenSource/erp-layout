package com.erp.erp_layout.modules.mapper;

import com.erp.erp_layout.modules.domain.*;
import com.erp.erp_layout.modules.dto.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModuleSchemaMapper {

    @Mapping(target = "dashboard", expression = "java(toDashboardDto(schema))")
    ModuleSchemaDto toDto(ModuleSchema schema);

    NavItemDto toDto(NavItem navItem);

    ViewDefDto toDto(ViewDef viewDef);

    @Mapping(target = "badgeOptions", source = "badgeOptions")
    ColumnDefDto toDto(ColumnDef columnDef);

    @Mapping(target = "form", source = "formId")
    ActionDefDto toDto(ActionDef actionDef);

    @Mapping(target = "fields", source = "fields")
    FormDefDto toDto(FormDef formDef);

    @Mapping(target = "defaultValue", source = "defaultValue")
    @Mapping(target = "fields", ignore = true) // repeater fields se cargan aparte
    FormFieldDto toDto(FormField formField);

    WidgetDefDto toDto(DashboardWidget widget);

    List<ModuleSchemaDto> toDtoList(List<ModuleSchema> schemas);

    default DashboardDto toDashboardDto(ModuleSchema schema) {
        if (schema.getWidgets() == null || schema.getWidgets().isEmpty()) {
            return null;
        }
        DashboardDto dto = new DashboardDto();
        dto.setWidgets(schema.getWidgets().stream().map(this::toDto).toList());
        return dto;
    }
}