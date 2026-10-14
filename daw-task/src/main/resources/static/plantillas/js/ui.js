/**
 * Módulo de Presentación DOM (js/ui.js)
 * FUNCIONES AUXILIARES REUTILIZABLES - NO SUELE TOCARSE
 *
 * Instrucciones:
 * - Adapta renderTabla() para que pinte las columnas de tu entidad
 * - openModalDetalle() muestra todos los campos del registro seleccionado
 * - openModalForm() gestiona tanto el modo Crear (null) como el modo Editar (objeto)
 * - El resto de funciones son genéricas y no es necesario modificarlas
 */

export const UIService = {

    elements: {
        tbody:            document.getElementById('tabla-tbody'),
        loadingState:     document.getElementById('loading-state'),
        emptyState:       document.getElementById('empty-state'),
        modalForm:        document.getElementById('modal-form'),
        modalFormTitulo:  document.getElementById('modal-form-titulo'),
        formEntidad:      document.getElementById('form-entidad'),
        modalDetalle:     document.getElementById('modal-detalle'),
        detalleContent:   document.getElementById('detalle-content'),
        toastContainer:   document.getElementById('toast-container'),
        statTotal:        document.getElementById('stat-total')
    },

    // ------------------------------------------------------------------
    // TABLA
    // ------------------------------------------------------------------

    showLoading() {
        if (this.elements.loadingState) this.elements.loadingState.classList.remove('hidden');
        if (this.elements.emptyState)   this.elements.emptyState.classList.add('hidden');
        if (this.elements.tbody)        this.elements.tbody.innerHTML = '';
    },

    /**
     * [ALUMNO] Adaptar el map() para pintar las columnas de tu entidad.
     * Regla: una celda <td> por campo. Sin HTML anidado en la celda.
     * Los campos de texto largo (descripcion, etc.) NO se muestran en tabla.
     */
    renderTabla(items) {
        if (this.elements.loadingState) this.elements.loadingState.classList.add('hidden');

        if (!items || items.length === 0) {
            if (this.elements.emptyState) this.elements.emptyState.classList.remove('hidden');
            if (this.elements.tbody)      this.elements.tbody.innerHTML = '';
            if (this.elements.statTotal)  this.elements.statTotal.textContent = '0';
            return;
        }

        if (this.elements.emptyState) this.elements.emptyState.classList.add('hidden');
        if (this.elements.statTotal)  this.elements.statTotal.textContent = items.length;

        this.elements.tbody.innerHTML = items.map(item => `
            <tr>
                <td><strong>#${item.id}</strong></td>
                <td>${this.escapeHTML(item.campo1)}</td>
                <td>${this.escapeHTML(item.campo2)}</td>
                <td style="text-align: center;">
                    <button class="btn-action btn-ver"    data-id="${item.id}" title="Ver Detalle">👁️ Ver</button>
                    <button class="btn-action btn-editar" data-id="${item.id}" title="Editar">✏️ Editar</button>
                    <button class="btn-action btn-action-danger btn-borrar" data-id="${item.id}" title="Eliminar">🗑️ Borrar</button>
                </td>
            </tr>
        `).join('');
    },

    // ------------------------------------------------------------------
    // MODAL FORMULARIO (Crear / Editar)
    // ------------------------------------------------------------------

    /**
     * [ALUMNO] Adaptar el bloque "MODO EDICIÓN" para rellenar los campos
     * de tu entidad a partir del objeto recibido del backend.
     *
     * @param {Object|null} item - null = Crear, objeto = Editar
     */
    openModalForm(item = null) {
        this.elements.formEntidad.reset();

        if (item) {
            // MODO EDICIÓN: rellenar cada input con los datos del registro
            this.elements.modalFormTitulo.textContent = `Editar Registro #${item.id}`;
            document.getElementById('campo-id').value     = item.id;
            // [ALUMNO] Rellenar aquí el resto de campos:
            // document.getElementById('campo-texto').value  = item.campo1 || '';
            // document.getElementById('campo-fecha').value  = item.campofecha || '';
            // document.getElementById('campo-select').value = item.campoEnum || '';
        } else {
            // MODO CREACIÓN: limpiar el campo oculto de ID
            this.elements.modalFormTitulo.textContent = 'Nuevo Registro';
            document.getElementById('campo-id').value = '';
        }

        this.elements.modalForm.classList.remove('hidden');
    },

    closeModalForm() {
        this.elements.modalForm.classList.add('hidden');
    },

    // ------------------------------------------------------------------
    // MODAL DETALLE (Ver)
    // ------------------------------------------------------------------

    /**
     * [ALUMNO] Adaptar el innerHTML para mostrar los campos de tu entidad.
     * Usar el patrón detail-item / detail-label / detail-value.
     */
    openModalDetalle(item) {
        this.elements.detalleContent.innerHTML = `
            <div class="detail-item">
                <div class="detail-label">ID</div>
                <div class="detail-value"><strong>#${item.id}</strong></div>
            </div>
            <div class="detail-item">
                <div class="detail-label">Campo 1</div>
                <div class="detail-value">${this.escapeHTML(item.campo1)}</div>
            </div>
            <div class="detail-item">
                <div class="detail-label">Campo 2 (texto largo)</div>
                <div class="detail-value">${this.escapeHTML(item.campo2 || 'Sin datos.')}</div>
            </div>
        `;
        this.elements.modalDetalle.classList.remove('hidden');
    },

    closeModalDetalle() {
        this.elements.modalDetalle.classList.add('hidden');
    },

    // ------------------------------------------------------------------
    // TOAST
    // ------------------------------------------------------------------

    showToast(message, type = 'success') {
        const toast = document.createElement('div');
        toast.className = `toast toast-${type}`;
        toast.innerHTML = `
            <span>${type === 'success' ? '✅' : '❌'}</span>
            <div>${this.escapeHTML(message)}</div>
        `;
        if (this.elements.toastContainer) {
            this.elements.toastContainer.appendChild(toast);
            setTimeout(() => {
                toast.style.opacity = '0';
                toast.style.transition = 'opacity 0.3s ease';
                setTimeout(() => toast.remove(), 300);
            }, 4000);
        }
    },

    // ------------------------------------------------------------------
    // UTILIDAD
    // ------------------------------------------------------------------

    escapeHTML(str) {
        if (!str) return '';
        return String(str)
            .replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;')
            .replace(/'/g, '&#039;');
    }
};
