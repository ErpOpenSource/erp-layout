-- Define vista/formulario de usuarios en SETTINGS
INSERT INTO module_views (id, module_id, label, type, endpoint, sort_order) VALUES
('users', 'SETTINGS', 'Usuarios', 'custom', '/auth/admin/users', 1);

INSERT INTO module_view_actions (view_id, module_id, type, label, icon, permission, confirm, form_id, sort_order) VALUES
('users', 'SETTINGS', 'create', 'Nuevo usuario', NULL, 'admin.users.create', false, 'user-create', 0);

INSERT INTO module_forms (id, module_id, label, endpoint, method) VALUES
('user-create', 'SETTINGS', 'Nuevo usuario', '/auth/admin/users', 'POST');

INSERT INTO module_form_fields (form_id, module_id, name, label, type, required, sort_order) VALUES
('user-create', 'SETTINGS', 'username', 'Usuario',    'text',     true, 0),
('user-create', 'SETTINGS', 'email',    'Email',      'email',    true, 1),
('user-create', 'SETTINGS', 'password', 'Contrasena', 'password', true, 2);
