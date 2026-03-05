package com.erp.erp_layout.modules.domain;

import com.fasterxml.jackson.databind.JsonNode;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "module_view_columns")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColumnDef {

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
    private String field;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String type;

    private boolean sortable = false;
    private boolean filterable = false;
    private Integer width;

    @Type(JsonBinaryType.class)
    @Column(name = "badge_options", columnDefinition = "jsonb")
    private JsonNode badgeOptions;

    @Column(name = "sort_order")
    private int sortOrder = 0;
}