package com.erp.erp_layout.modules.domain;

import lombok.*;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class NavItemId implements Serializable {
    private String id;
    private String moduleId;
}