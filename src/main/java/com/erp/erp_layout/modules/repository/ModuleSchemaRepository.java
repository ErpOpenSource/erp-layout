package com.erp.erp_layout.modules.repository;

import com.erp.erp_layout.modules.domain.ModuleSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleSchemaRepository extends JpaRepository<ModuleSchema, String> {

    List<ModuleSchema> findByEnabledTrueOrderBySortOrderAsc();
@Query("""
    SELECT DISTINCT m FROM ModuleSchema m
    LEFT JOIN FETCH m.navItems
    LEFT JOIN FETCH m.views v
    LEFT JOIN FETCH v.columns
    LEFT JOIN FETCH v.actions
    LEFT JOIN FETCH m.widgets
    WHERE m.id = :id AND m.enabled = true
""")
Optional<ModuleSchema> findByIdWithDetails(@Param("id") String id);

    @Query("""
        SELECT DISTINCT m FROM ModuleSchema m
        LEFT JOIN FETCH m.navItems
        WHERE m.id IN :ids AND m.enabled = true
        ORDER BY m.sortOrder ASC
    """)
    List<ModuleSchema> findByIdInWithNavItems(@Param("ids") List<String> ids);
}