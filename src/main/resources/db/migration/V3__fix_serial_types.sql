-- Corrige las columnas SERIAL a BIGSERIAL para que coincidan con Hibernate 7

ALTER TABLE module_view_columns ALTER COLUMN id TYPE BIGINT;
ALTER TABLE module_view_actions ALTER COLUMN id TYPE BIGINT;
ALTER TABLE module_form_fields ALTER COLUMN id TYPE BIGINT;

-- Recrea las secuencias como BIGINT
ALTER SEQUENCE module_view_columns_id_seq AS BIGINT;
ALTER SEQUENCE module_view_actions_id_seq AS BIGINT;
ALTER SEQUENCE module_form_fields_id_seq AS BIGINT;