package com.erp.erp_layout.modules.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "module_forms")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(FormDefId.class)
public class FormDef {

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
    private String endpoint;

    @Column(nullable = false)
    private String method;

@OneToMany(mappedBy = "formDef", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@OrderBy("sortOrder ASC")
private Set<FormField> fields = new LinkedHashSet<>();
}