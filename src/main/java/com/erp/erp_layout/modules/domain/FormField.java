package com.erp.erp_layout.modules.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "module_form_fields")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "form_id", nullable = false)
    private String formId;

    @Column(name = "module_id", nullable = false)
    private String moduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "form_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "module_id", referencedColumnName = "module_id", insertable = false, updatable = false)
    })
    private FormDef formDef;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String type;

    private boolean required = false;
    private String placeholder;

    @Column(name = "default_value")
    private String defaultValue;

    private String source;

    @Column(name = "parent_field")
    private String parentField;

    @Column(name = "sort_order")
    private int sortOrder = 0;
}