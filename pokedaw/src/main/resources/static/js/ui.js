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

        this.elements.tbody.innerHTML = items.map(pokemon => `
            <tr>
                <td><strong>#${pokemon.id}</strong></td>
                <td>${pokemon.numeroPokedex}</td>
				<td>${pokemon.nombre}</td>
				<td>${pokemon.tipo1}</td>
				<td>${pokemon.tipo2}</td>
				<td>${pokemon.fechaCaptura}</td>
				<td>${pokemon.capturado}</td>
                <td style="text-align: center;">
                    <button class="btn-action btn-ver"    data-id="${pokemon.id}" title="Ver Detalle">👁️ Ver</button>
                    <button class="btn-action btn-editar" data-id="${pokemon.id}" title="Editar">✏️ Editar</button>
                    <button class="btn-action btn-action-danger btn-borrar" data-id="${pokemon.id}" title="Eliminar">🗑️ Borrar</button>
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
     * @param {Object|null} pokemon - null = Crear, objeto = Editar
     */
    openModalForm(pokemon = null) {
        this.elements.formEntidad.reset();

        if (pokemon) {
            // MODO EDICIÓN: rellenar cada input con los datos del registro
            this.elements.modalFormTitulo.textContent = `Editar pokemon #${pokemon.id}`;
            document.getElementById('pokemon-id').value = pokemon.id;
            // [ALUMNO] Rellenar aquí el resto de campos:
            document.getElementById('pokemon-numero').value  = pokemon.numeroPokedex || '';
			document.getElementById('pokemon-nombre').value  = pokemon.nombre || '';
			document.getElementById('pokemon-tipo1').value  = pokemon.tipo1 || '';
			document.getElementById('pokemon-tipo2').value  = pokemon.tipo2 || '';
			document.getElementById('pokemon-capturado').value  = pokemon.capturado || '';
        } else {
            // MODO CREACIÓN: limpiar el campo oculto de ID
            this.elements.modalFormTitulo.textContent = 'Nuevo pokemon';
			document.getElementById('pokemon-id').value     = '';
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
    openModalDetalle(pokemon) {
        this.elements.detalleContent.innerHTML = `
            <div class="detail-item">
                <div class="detail-label">ID</div>
                <div class="detail-value"><strong>#${pokemon.id}</strong></div>
            </div>
            <div class="detail-item">
                <div class="detail-label">Nº Pokédex</div>
                <div class="detail-value">${pokemon.numeroPokedex}</div>
            </div>
            <div class="detail-item">
                <div class="detail-label">Nombre</div>
                <div class="detail-value">${pokemon.nombre || 'Sin datos.'}</div>
            </div>
			<div class="detail-item">
			    <div class="detail-label">Tipo 1</div>
			    <div class="detail-value">${pokemon.tipo1 || 'Sin datos.'}</div>
			</div>
			<div class="detail-item">
			    <div class="detail-label">Tipo 2</div>
			    <div class="detail-value">${pokemon.tipo2 || 'Sin datos.'}</div>
			</div>
			<div class="detail-item">
			    <div class="detail-label">Fecha de captura</div>
			    <div class="detail-value">${pokemon.fechaCaptura}</div>
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
