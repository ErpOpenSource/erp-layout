package com.erp.erp_layout.modules.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dashboard_widgets")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardWidget {

    @Id
    private String id;

    @Column(name = "module_id", nullable = false)
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

    private String icon;
    private String color;

    private int span = 1;

    @Column(name = "sort_order")
    private int sortOrder = 0;
}