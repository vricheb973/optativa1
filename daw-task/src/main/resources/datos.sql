INSERT INTO tarea (titulo, descripcion, fecha_creacion, fecha_vencimiento, estado) VALUES
('Diseñar maqueta UI', 'Crear el prototipo de alta fidelidad para el panel principal.', '2026-09-01', '2026-09-10', 'COMPLETADA'),
('Configurar base de datos', 'Instalar MariaDB y ejecutar los scripts de migración iniciales.', '2026-09-05', '2026-09-12', 'COMPLETADA'),
('Implementar autenticación', 'Desarrollar el flujo de login y registro usando Spring Security y JWT.', '2026-09-10', '2026-09-25', 'EN_PROGRESO'),
('Crear API de Tareas', 'Implementar los endpoints CRUD para la entidad TareaEntity.', '2026-09-15', '2026-10-05', 'EN_PROGRESO'),
('Optimizar consultas SQL', 'Revisar índices y planes de ejecución para mejorar el rendimiento.', '2026-09-20', '2026-10-10', 'PENDIENTE'),
('Pruebas unitarias', 'Escribir tests de integración y unitarios con JUnit y Mockito.', '2026-09-22', '2026-10-15', 'PENDIENTE'),
('Documentar API', 'Generar la documentación OpenAPI/Swagger para el equipo de Frontend.', '2026-09-25', '2026-10-02', 'PENDIENTE'),
('Desplegar en Staging', 'Configurar el pipeline de CI/CD para desplegar en el entorno de pruebas.', '2026-09-28', '2026-10-12', 'PENDIENTE'),
('Corregir bug de fechas', 'Ajustar la zona horaria en la conversión de fechas al guardar registros.', '2026-09-29', '2026-10-01', 'EN_PROGRESO'),
('Refactorizar repositorios', 'Migrar consultas nativas a Spring Data JPA derivando nombres de métodos.', '2026-09-30', '2026-10-20', 'PENDIENTE');