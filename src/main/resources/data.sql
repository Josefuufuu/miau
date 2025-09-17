INSERT INTO permissions (id, name, description) VALUES
    (1, 'USER_READ', 'Permite consultar usuarios'),
    (2, 'USER_WRITE', 'Permite crear y actualizar usuarios'),
    (3, 'PROJECT_MANAGE', 'Gestiona proyectos y tareas'),
    (4, 'REPORT_VIEW', 'Permite ver reportes de progreso');

INSERT INTO roles (id, name, description) VALUES
    (1, 'ADMIN', 'Administrador con todos los permisos'),
    (2, 'PROJECT_MANAGER', 'Responsable de proyectos y reportes');

INSERT INTO role_permissions (role_id, permission_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 1),
    (2, 3),
    (2, 4);

INSERT INTO users (id, username, email, password) VALUES
    (1, 'admin', 'admin@example.com', '{noop}admin123'),
    (2, 'pmaria', 'pmaria@example.com', '{noop}pmaria123');

INSERT INTO user_roles (user_id, role_id) VALUES
    (1, 1),
    (2, 2);

INSERT INTO projects (id, name, description, start_date, end_date, manager_id) VALUES
    (1, 'Plataforma de Gestión', 'Proyecto para digitalizar la gestión interna', '2024-01-10', '2024-06-30', 2);

INSERT INTO tasks (id, title, description, status, due_date, project_id, assigned_to) VALUES
    (1, 'Definir alcance', 'Reunión inicial para definir entregables', 'COMPLETED', '2024-02-01', 1, 2),
    (2, 'Diseñar base de datos', 'Crear modelo relacional para la plataforma', 'IN_PROGRESS', '2024-03-15', 1, 2);
