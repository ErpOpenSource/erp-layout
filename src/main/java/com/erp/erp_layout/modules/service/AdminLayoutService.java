package com.erp.erp_layout.modules.service;

import com.erp.erp_layout.modules.domain.FormDef;
import com.erp.erp_layout.modules.domain.FormDefId;
import com.erp.erp_layout.modules.domain.ModuleSchema;
import com.erp.erp_layout.modules.domain.NavItem;
import com.erp.erp_layout.modules.domain.NavItemId;
import com.erp.erp_layout.modules.domain.ViewDef;
import com.erp.erp_layout.modules.domain.ViewDefId;
import com.erp.erp_layout.modules.dto.ModuleSchemaDto;
import com.erp.erp_layout.modules.dto.admin.CreateFormRequest;
import com.erp.erp_layout.modules.dto.admin.CreateModuleRequest;
import com.erp.erp_layout.modules.dto.admin.CreateNavItemRequest;
import com.erp.erp_layout.modules.dto.admin.CreateViewRequest;
import com.erp.erp_layout.modules.dto.admin.UpdateFormRequest;
import com.erp.erp_layout.modules.dto.admin.UpdateModuleRequest;
import com.erp.erp_layout.modules.dto.admin.UpdateNavItemRequest;
import com.erp.erp_layout.modules.dto.admin.UpdateViewRequest;
import com.erp.erp_layout.modules.mapper.ModuleSchemaMapper;
import com.erp.erp_layout.modules.repository.FormDefRepository;
import com.erp.erp_layout.modules.repository.ModuleSchemaRepository;
import com.erp.erp_layout.modules.repository.NavItemRepository;
import com.erp.erp_layout.modules.repository.ViewDefRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminLayoutService {

    private final ModuleSchemaRepository moduleRepo;
    private final NavItemRepository navItemRepo;
    private final ViewDefRepository viewDefRepo;
    private final FormDefRepository formDefRepo;
    private final ModuleSchemaMapper mapper;

    // ── Modules ──────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<ModuleSchemaDto> listModules() {
        return moduleRepo.findAll(Sort.by("sortOrder"))
                .stream().map(mapper::toDto).toList();
    }

    @CacheEvict(value = "module-schema", allEntries = true)
    public ModuleSchemaDto createModule(CreateModuleRequest req) {
        if (moduleRepo.existsById(req.id())) {
            throw new IllegalArgumentException("Module id already exists: " + req.id());
        }
        ModuleSchema m = ModuleSchema.builder()
                .id(req.id().toUpperCase())
                .label(req.label())
                .icon(req.icon())
                .color(req.color())
                .enabled(true)
                .sortOrder(req.sortOrder())
                .build();
        return mapper.toDto(moduleRepo.save(m));
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public ModuleSchemaDto updateModule(String moduleId, UpdateModuleRequest req) {
        ModuleSchema m = requireModule(moduleId);
        m.setLabel(req.label());
        m.setIcon(req.icon());
        m.setColor(req.color());
        if (req.enabled() != null) m.setEnabled(req.enabled());
        if (req.sortOrder() != null) m.setSortOrder(req.sortOrder());
        return mapper.toDto(moduleRepo.save(m));
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public void deleteModule(String moduleId) {
        moduleRepo.delete(requireModule(moduleId));
    }

    // ── Nav items ─────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<?> listNavItems(String moduleId) {
        requireModule(moduleId);
        return navItemRepo.findByModuleIdOrderBySortOrderAsc(moduleId)
                .stream().map(mapper::toDto).toList();
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public Object createNavItem(String moduleId, CreateNavItemRequest req) {
        requireModule(moduleId);
        NavItemId pk = new NavItemId(req.id(), moduleId);
        if (navItemRepo.existsById(pk)) {
            throw new IllegalArgumentException("NavItem id already exists in module: " + req.id());
        }
        NavItem item = NavItem.builder()
                .id(req.id())
                .moduleId(moduleId)
                .label(req.label())
                .icon(req.icon())
                .path(req.path())
                .permission(req.permission())
                .sortOrder(req.sortOrder())
                .build();
        return mapper.toDto(navItemRepo.save(item));
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public Object updateNavItem(String moduleId, String navItemId, UpdateNavItemRequest req) {
        NavItem item = navItemRepo.findById(new NavItemId(navItemId, moduleId))
                .orElseThrow(() -> new EntityNotFoundException("NavItem not found: " + navItemId));
        item.setLabel(req.label());
        item.setIcon(req.icon());
        item.setPath(req.path());
        item.setPermission(req.permission());
        item.setSortOrder(req.sortOrder());
        return mapper.toDto(navItemRepo.save(item));
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public void deleteNavItem(String moduleId, String navItemId) {
        NavItem item = navItemRepo.findById(new NavItemId(navItemId, moduleId))
                .orElseThrow(() -> new EntityNotFoundException("NavItem not found: " + navItemId));
        navItemRepo.delete(item);
    }

    // ── Views ─────────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<?> listViews(String moduleId) {
        requireModule(moduleId);
        return viewDefRepo.findByModuleIdOrderBySortOrderAsc(moduleId)
                .stream().map(mapper::toDto).toList();
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public Object createView(String moduleId, CreateViewRequest req) {
        requireModule(moduleId);
        ViewDefId pk = new ViewDefId(req.id(), moduleId);
        if (viewDefRepo.existsById(pk)) {
            throw new IllegalArgumentException("View id already exists in module: " + req.id());
        }
        ViewDef v = ViewDef.builder()
                .id(req.id())
                .moduleId(moduleId)
                .label(req.label())
                .type(req.type())
                .endpoint(req.endpoint())
                .sortOrder(req.sortOrder())
                .build();
        return mapper.toDto(viewDefRepo.save(v));
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public Object updateView(String moduleId, String viewId, UpdateViewRequest req) {
        ViewDef v = viewDefRepo.findById(new ViewDefId(viewId, moduleId))
                .orElseThrow(() -> new EntityNotFoundException("View not found: " + viewId));
        v.setLabel(req.label());
        v.setType(req.type());
        v.setEndpoint(req.endpoint());
        v.setSortOrder(req.sortOrder());
        return mapper.toDto(viewDefRepo.save(v));
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public void deleteView(String moduleId, String viewId) {
        ViewDef v = viewDefRepo.findById(new ViewDefId(viewId, moduleId))
                .orElseThrow(() -> new EntityNotFoundException("View not found: " + viewId));
        viewDefRepo.delete(v);
    }

    // ── Forms ─────────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<?> listForms(String moduleId) {
        requireModule(moduleId);
        return formDefRepo.findByModuleId(moduleId)
                .stream().map(mapper::toDto).toList();
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public Object createForm(String moduleId, CreateFormRequest req) {
        requireModule(moduleId);
        FormDefId pk = new FormDefId(req.id(), moduleId);
        if (formDefRepo.existsById(pk)) {
            throw new IllegalArgumentException("Form id already exists in module: " + req.id());
        }
        FormDef f = FormDef.builder()
                .id(req.id())
                .moduleId(moduleId)
                .label(req.label())
                .endpoint(req.endpoint())
                .method(req.method())
                .build();
        return mapper.toDto(formDefRepo.save(f));
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public Object updateForm(String moduleId, String formId, UpdateFormRequest req) {
        FormDef f = formDefRepo.findById(new FormDefId(formId, moduleId))
                .orElseThrow(() -> new EntityNotFoundException("Form not found: " + formId));
        f.setLabel(req.label());
        f.setEndpoint(req.endpoint());
        f.setMethod(req.method());
        return mapper.toDto(formDefRepo.save(f));
    }

    @CacheEvict(value = "module-schema", key = "#moduleId")
    public void deleteForm(String moduleId, String formId) {
        FormDef f = formDefRepo.findById(new FormDefId(formId, moduleId))
                .orElseThrow(() -> new EntityNotFoundException("Form not found: " + formId));
        formDefRepo.delete(f);
    }

    // ─────────────────────────────────────────────────────────────────────────

    private ModuleSchema requireModule(String moduleId) {
        return moduleRepo.findById(moduleId)
                .orElseThrow(() -> new EntityNotFoundException("Module not found: " + moduleId));
    }
}
