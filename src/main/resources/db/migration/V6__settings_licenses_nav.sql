-- V6: Add "Licencias" nav item to SETTINGS module
INSERT INTO module_nav_items (id, module_id, label, icon, path, permission, sort_order)
VALUES ('licenses', 'SETTINGS', 'Licencias', 'key', '/settings/licenses', 'admin.seats.read', 3);
