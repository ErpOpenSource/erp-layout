package com.erp.erp_layout.modules.service;

import com.erp.erp_layout.modules.domain.ModuleSchema;
import com.erp.erp_layout.modules.dto.ModuleSchemaDto;
import com.erp.erp_layout.modules.mapper.ModuleSchemaMapper;
import com.erp.erp_layout.modules.repository.ModuleSchemaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ModuleSchemaService {

    private final ModuleSchemaRepository repository;
    private final ModuleSchemaMapper mapper;

@Cacheable(value = "module-schema", key = "#moduleId")
public ModuleSchemaDto getSchema(String moduleId) {
    return repository.findByIdWithDetails(moduleId)
            .map(mapper::toDto)
            .orElseThrow(() -> new EntityNotFoundException("Module not found: " + moduleId));
}

    public List<ModuleSchemaDto> getSchemasByIds(List<String> moduleIds) {
        return repository.findByIdInWithNavItems(moduleIds)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<ModuleSchemaDto> getAllEnabled() {
        return repository.findByEnabledTrueOrderBySortOrderAsc()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}