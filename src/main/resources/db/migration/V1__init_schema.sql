-- Tabla de schemas de módulos
CREATE TABLE module_schemas (
    id          VARCHAR(50) PRIMARY KEY,
    label       VARCHAR(100) NOT NULL,
    icon        VARCHAR(100) NOT NULL,
    color       VARCHAR(20) NOT NULL,
    enabled     BOOLEAN NOT NULL DEFAULT true,
    sort_order  INTEGER NOT NULL DEFAULT 0,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Nav items de cada módulo
CREATE TABLE module_nav_items (
    id          VARCHAR(50) NOT NULL,
    module_id   VARCHAR(50) NOT NULL REFERENCES module_schemas(id) ON DELETE CASCADE,
    label       VARCHAR(100) NOT NULL,
    icon        VARCHAR(100) NOT NULL,
    path        VARCHAR(255) NOT NULL,
    permission  VARCHAR(100),
    badge_endpoint VARCHAR(255),
    sort_order  INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY (id, module_id)
);

-- Vistas de cada módulo
CREATE TABLE module_views (
    id          VARCHAR(50) NOT NULL,
    module_id   VARCHAR(50) NOT NULL REFERENCES module_schemas(id) ON DELETE CASCADE,
    label       VARCHAR(100) NOT NULL,
    type        VARCHAR(50) NOT NULL DEFAULT 'table',
    endpoint    VARCHAR(255) NOT NULL,
    sort_order  INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY (id, module_id)
);

-- Columnas de cada vista (guardadas como JSON por flexibilidad)
CREATE TABLE module_view_columns (
    id          BIGSERIAL PRIMARY KEY,
    view_id     VARCHAR(50) NOT NULL,
    module_id   VARCHAR(50) NOT NULL,
    field       VARCHAR(100) NOT NULL,
    label       VARCHAR(100) NOT NULL,
    type        VARCHAR(50) NOT NULL DEFAULT 'text',
    sortable    BOOLEAN NOT NULL DEFAULT false,
    filterable  BOOLEAN NOT NULL DEFAULT false,
    width       INTEGER,
    badge_options JSONB,
    sort_order  INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (view_id, module_id) REFERENCES module_views(id, module_id) ON DELETE CASCADE
);

-- Acciones de cada vista
CREATE TABLE module_view_actions (
    id          BIGSERIAL PRIMARY KEY,
    view_id     VARCHAR(50) NOT NULL,
    module_id   VARCHAR(50) NOT NULL,
    type        VARCHAR(50) NOT NULL,
    label       VARCHAR(100) NOT NULL,
    icon        VARCHAR(100),
    permission  VARCHAR(100),
    confirm     BOOLEAN NOT NULL DEFAULT false,
    form_id     VARCHAR(50),
    sort_order  INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (view_id, module_id) REFERENCES module_views(id, module_id) ON DELETE CASCADE
);

-- Formularios de cada módulo
CREATE TABLE module_forms (
    id          VARCHAR(50) NOT NULL,
    module_id   VARCHAR(50) NOT NULL REFERENCES module_schemas(id) ON DELETE CASCADE,
    label       VARCHAR(100) NOT NULL,
    endpoint    VARCHAR(255) NOT NULL,
    method      VARCHAR(10) NOT NULL DEFAULT 'POST',
    PRIMARY KEY (id, module_id)
);

-- Campos de cada formulario
CREATE TABLE module_form_fields (
    id          BIGSERIAL PRIMARY KEY,
    form_id     VARCHAR(50) NOT NULL,
    module_id   VARCHAR(50) NOT NULL,
    name        VARCHAR(100) NOT NULL,
    label       VARCHAR(100) NOT NULL,
    type        VARCHAR(50) NOT NULL DEFAULT 'text',
    required    BOOLEAN NOT NULL DEFAULT false,
    placeholder VARCHAR(255),
    default_value VARCHAR(255),
    source      VARCHAR(255),
    parent_field VARCHAR(100),
    sort_order  INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (form_id, module_id) REFERENCES module_forms(id, module_id) ON DELETE CASCADE
);

-- Widgets del dashboard
CREATE TABLE dashboard_widgets (
    id          VARCHAR(50) PRIMARY KEY,
    module_id   VARCHAR(50) NOT NULL REFERENCES module_schemas(id) ON DELETE CASCADE,
    label       VARCHAR(100) NOT NULL,
    type        VARCHAR(50) NOT NULL,
    endpoint    VARCHAR(255) NOT NULL,
    icon        VARCHAR(100),
    color       VARCHAR(20),
    span        INTEGER NOT NULL DEFAULT 1,
    sort_order  INTEGER NOT NULL DEFAULT 0
);

-- Preferencias de usuario
CREATE TABLE user_preferences (
    id          BIGSERIAL PRIMARY KEY,
    user_id     VARCHAR(100) NOT NULL UNIQUE,
    theme       VARCHAR(20) NOT NULL DEFAULT 'system',
    language    VARCHAR(10) NOT NULL DEFAULT 'es',
    sidebar_collapsed BOOLEAN NOT NULL DEFAULT false,
    module_prefs JSONB NOT NULL DEFAULT '{}',
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Índices
CREATE INDEX idx_module_nav_items_module ON module_nav_items(module_id);
CREATE INDEX idx_module_views_module ON module_views(module_id);
CREATE INDEX idx_user_preferences_user ON user_preferences(user_id);