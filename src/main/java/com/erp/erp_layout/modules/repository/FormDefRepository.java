package com.erp.erp_layout.modules.repository;

import com.erp.erp_layout.modules.domain.FormDef;
import com.erp.erp_layout.modules.domain.FormDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormDefRepository extends JpaRepository<FormDef, FormDefId> {
    List<FormDef> findByModuleId(String moduleId);
    void deleteByModuleId(String moduleId);
}
