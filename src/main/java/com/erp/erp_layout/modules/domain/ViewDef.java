package com.erp.erp_layout.modules.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "module_views")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ViewDefId.class)
public class ViewDef {

    @Id
    private String id;

    @Id
    @Column(name = "module_id")
    private String moduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", insertable = false, updatable = false)
    private ModuleSchema moduleSchema;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String endpoint;

    @Column(name = "sort_order")
    private int sortOrder = 0;
@OneToMany(mappedBy = "viewDef", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@OrderBy("sortOrder ASC")
private Set<ColumnDef> columns = new LinkedHashSet<>();

@OneToMany(mappedBy = "viewDef", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@OrderBy("sortOrder ASC")
private Set<ActionDef> actions = new LinkedHashSet<>();
}