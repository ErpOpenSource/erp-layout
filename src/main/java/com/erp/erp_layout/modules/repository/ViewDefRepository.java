package com.erp.erp_layout.modules.repository;

import com.erp.erp_layout.modules.domain.ViewDef;
import com.erp.erp_layout.modules.domain.ViewDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewDefRepository extends JpaRepository<ViewDef, ViewDefId> {
    List<ViewDef> findByModuleIdOrderBySortOrderAsc(String moduleId);
    void deleteByModuleId(String moduleId);
}
