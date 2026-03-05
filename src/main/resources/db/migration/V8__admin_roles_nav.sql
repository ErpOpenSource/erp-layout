-- V8: Add Roles nav item to ADMIN module (between Departamentos and Módulos)

-- Shift sort_order of items from 4 onwards to make room
UPDATE module_nav_items SET sort_order = sort_order + 1
WHERE module_id = 'ADMIN' AND sort_order >= 4;

-- Insert Roles at sort_order 4
INSERT INTO module_nav_items (id, module_id, label, icon, path, permission, sort_order)
VALUES ('roles', 'ADMIN', 'Roles', 'shield-check', '/admin/roles', NULL, 4);
