package com.erp.erp_layout.modules.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "module_nav_items")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(NavItemId.class)
public class NavItem {

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
    private String icon;

    @Column(nullable = false)
    private String path;

    private String permission;

    @Column(name = "badge_endpoint")
    private String badgeEndpoint;

    @Column(name = "sort_order")
    private int sortOrder = 0;
}