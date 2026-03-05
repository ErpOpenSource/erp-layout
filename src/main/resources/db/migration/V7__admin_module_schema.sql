-- V7: Add ADMIN module + move admin nav items from SETTINGS to ADMIN

-- 1. Remove admin-specific items from SETTINGS (keep only profile)
DELETE FROM module_nav_items WHERE module_id = 'SETTINGS' AND id IN ('users', 'sessions', 'licenses');

-- 2. Create ADMIN module
INSERT INTO module_schemas (id, label, icon, color, sort_order)
VALUES ('ADMIN', 'Administración', 'shield-check', '#7c3aed', 98);

-- 3. Add all nav items for ADMIN module
INSERT INTO module_nav_items (id, module_id, label, icon, path, permission, sort_order) VALUES
('users',       'ADMIN', 'Usuarios',      'users',           '/admin/users',       NULL, 0),
('sessions',    'ADMIN', 'Sesiones',      'monitor',         '/admin/sessions',    NULL, 1),
('licenses',    'ADMIN', 'Licencias',     'key',             '/admin/licenses',    NULL, 2),
('departments', 'ADMIN', 'Departamentos', 'building-2',      '/admin/departments', NULL, 3),
('modules',     'ADMIN', 'Módulos',       'layout-dashboard','/admin/modules',     NULL, 4),
('views',       'ADMIN', 'Vistas',        'table-2',         '/admin/views',       NULL, 5),
('forms',       'ADMIN', 'Formularios',   'file-text',       '/admin/forms',       NULL, 6);
