INSERT INTO module_schemas (id, label, icon, color, sort_order) VALUES
('DASHBOARD', 'Inicio',  'layout-dashboard', '#6366f1', 0),
('SALES',     'Ventas',  'shopping-cart',    '#3b82f6', 1),
('SETTINGS',  'Ajustes', 'settings',         '#6b7280', 99);

-- Nav items SALES
INSERT INTO module_nav_items (id, module_id, label, icon, path, permission, sort_order) VALUES
('orders',    'SALES', 'Pedidos',      'shopping-cart', '/sales/orders',    'sales.order.read', 0),
('customers', 'SALES', 'Clientes',     'users',         '/sales/customers', NULL,               1),
('invoices',  'SALES', 'Facturas',     'receipt',       '/sales/invoices',  NULL,               2);

-- Nav items SETTINGS
INSERT INTO module_nav_items (id, module_id, label, icon, path, permission, sort_order) VALUES
('profile',  'SETTINGS', 'Mi perfil', 'user',    '/settings/profile',  NULL,                  0),
('users',    'SETTINGS', 'Usuarios',  'users',   '/settings/users',    'admin.seats.read',    1),
('sessions', 'SETTINGS', 'Sesiones',  'monitor', '/settings/sessions', 'admin.sessions.read', 2);

-- Vista orders
INSERT INTO module_views (id, module_id, label, type, endpoint, sort_order) VALUES
('orders',    'SALES', 'Pedidos',  'table', '/api/sales/orders',    0),
('customers', 'SALES', 'Clientes', 'table', '/api/sales/customers', 1);

-- Columnas orders
INSERT INTO module_view_columns (view_id, module_id, field, label, type, sortable, filterable, badge_options, sort_order) VALUES
('orders', 'SALES', 'id',       'Nº Pedido', 'text',     true,  false, NULL, 0),
('orders', 'SALES', 'customer', 'Cliente',   'text',     false, true,  NULL, 1),
('orders', 'SALES', 'total',    'Total',     'currency', true,  false, NULL, 2),
('orders', 'SALES', 'status',   'Estado',    'badge',    false, false,
    '{"PENDING":"yellow","COMPLETED":"green","CANCELLED":"red"}', 3),
('orders', 'SALES', 'date',     'Fecha',     'date',     true,  false, NULL, 4);

-- Columnas customers
INSERT INTO module_view_columns (view_id, module_id, field, label, type, sortable, filterable, sort_order) VALUES
('customers', 'SALES', 'name',   'Nombre',   'text', true,  true,  0),
('customers', 'SALES', 'email',  'Email',    'text', false, true,  1),
('customers', 'SALES', 'phone',  'Teléfono', 'text', false, false, 2);

-- Acciones orders
INSERT INTO module_view_actions (view_id, module_id, type, label, icon, permission, confirm, form_id, sort_order) VALUES
('orders', 'SALES', 'create', 'Nuevo pedido', NULL,     'sales.order.create', false, 'order-form', 0),
('orders', 'SALES', 'view',   'Ver',          'eye',    NULL,                 false, NULL,         1),
('orders', 'SALES', 'edit',   'Editar',       'pencil', NULL,                 false, NULL,         2),
('orders', 'SALES', 'delete', 'Eliminar',     'trash',  NULL,                 true,  NULL,         3);

-- Formulario order-form
INSERT INTO module_forms (id, module_id, label, endpoint, method) VALUES
('order-form', 'SALES', 'Nuevo pedido', '/api/sales/orders', 'POST');

INSERT INTO module_form_fields (form_id, module_id, name, label, type, required, sort_order) VALUES
('order-form', 'SALES', 'customer', 'Cliente', 'text',     true,  0),
('order-form', 'SALES', 'date',     'Fecha',   'date',     true,  1),
('order-form', 'SALES', 'notes',    'Notas',   'textarea', false, 2);

-- Widgets dashboard — solo los básicos de sales
INSERT INTO dashboard_widgets (id, module_id, label, type, endpoint, icon, color, span, sort_order) VALUES
('total-sales',    'DASHBOARD', 'Ventas hoy',         'kpi',        '/api/sales/stats/today',  'shopping-cart', '#3b82f6', 1, 0),
('pending-orders', 'DASHBOARD', 'Pedidos pendientes', 'kpi',        '/api/sales/orders/pending/count', 'clock', '#f59e0b', 1, 1),
('sales-chart',    'DASHBOARD', 'Ventas este mes',    'chart-line', '/api/sales/stats/daily',  NULL,            '#3b82f6', 2, 2);