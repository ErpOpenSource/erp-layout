package com.erp.erp_layout.modules.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "module_view_actions")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionDef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "view_id", nullable = false)
    private String viewId;

    @Column(name = "module_id", nullable = false)
    private String moduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "view_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "module_id", referencedColumnName = "module_id", insertable = false, updatable = false)
    })
    private ViewDef viewDef;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String label;

    private String icon;
    private String permission;
    private boolean confirm = false;

    @Column(name = "form_id")
    private String formId;

    @Column(name = "sort_order")
    private int sortOrder = 0;
}