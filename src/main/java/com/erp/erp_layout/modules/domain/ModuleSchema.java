package com.erp.erp_layout.modules.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "module_schemas")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleSchema {

    @Id
    private String id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String icon;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(name = "sort_order", nullable = false)
    private int sortOrder = 0;

@OneToMany(mappedBy = "moduleSchema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@OrderBy("sortOrder ASC")
private Set<NavItem> navItems = new LinkedHashSet<>();

@OneToMany(mappedBy = "moduleSchema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@OrderBy("sortOrder ASC")
private Set<ViewDef> views = new LinkedHashSet<>();

@OneToMany(mappedBy = "moduleSchema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private Set<FormDef> forms = new LinkedHashSet<>();

@OneToMany(mappedBy = "moduleSchema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@OrderBy("sortOrder ASC")
private Set<DashboardWidget> widgets = new LinkedHashSet<>();
}