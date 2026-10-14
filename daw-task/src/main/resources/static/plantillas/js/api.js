/**
 * Módulo de Integración API REST (js/api.js)
 * PLANTILLA BASE EN BLANCO PARA EL ALUMNO
 *
 * Instrucciones:
 * 1. Cambia BASE_URL al endpoint de tu entidad (ej: '/tareas', '/productos')
 * 2. Rellena los métodos o añade nuevos según los endpoints disponibles en el backend
 * 3. Reglas importantes del service de Spring Boot:
 *    - PUT: el campo 'id' del body DEBE coincidir con el id de la URL
 *    - POST/PUT: NO envíes campos que el service prohíba (ej: estado, fechaCreacion)
 */

const BASE_URL = '/cambiar-por-tu-endpoint'; // Ejemplo: '/tareas'

export const ApiService = {

    /**
     * GET ALL - Obtener todos los registros
     * Endpoint: GET /entidad
     */
    async getAll() {
        const response = await fetch(BASE_URL);
        if (!response.ok) {
            throw new Error(`Error HTTP ${response.status}: No se pudo cargar el listado.`);
        }
        return await response.json();
    },

    /**
     * GET BY ID - Obtener un registro por ID
     * Endpoint: GET /entidad/{id}
     */
    async getById(id) {
        const response = await fetch(`${BASE_URL}/${id}`);
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || `No se encontró el registro #${id}`);
        }
        return await response.json();
    },

    /**
     * POST - Crear un nuevo registro
     * Endpoint: POST /entidad
     * ⚠️ No incluir en data los campos que el service no permita (estado, fechaCreacion, etc.)
     */
    async create(data) {
        const response = await fetch(BASE_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || 'Error al crear el registro');
        }
        return await response.json();
    },

    /**
     * PUT - Actualizar un registro existente
     * Endpoint: PUT /entidad/{id}
     * ⚠️ El campo 'id' del body DEBE coincidir con el id de la URL.
     * ⚠️ No incluir en data los campos que el service no permita.
     */
    async update(id, data) {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || `Error al actualizar el registro #${id}`);
        }
        return await response.json();
    },

    /**
     * DELETE - Borrar un registro por ID
     * Endpoint: DELETE /entidad/{id}
     */
    async delete(id) {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || `Error al eliminar el registro #${id}`);
        }
        return true;
    }

    // [ALUMNO] Añadir aquí métodos adicionales si son necesarios
    // Ejemplo: endpoints de acción (/tareas/{id}/iniciar, etc.)
};
