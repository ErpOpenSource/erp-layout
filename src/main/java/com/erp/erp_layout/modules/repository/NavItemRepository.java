package com.erp.erp_layout.modules.repository;

import com.erp.erp_layout.modules.domain.NavItem;
import com.erp.erp_layout.modules.domain.NavItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavItemRepository extends JpaRepository<NavItem, NavItemId> {
    List<NavItem> findByModuleIdOrderBySortOrderAsc(String moduleId);
    void deleteByModuleId(String moduleId);
}
