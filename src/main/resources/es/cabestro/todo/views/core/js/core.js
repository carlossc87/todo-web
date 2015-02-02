/* 
 * Copyright (C) 2014 Carlos Serramito Calvo <carlos@cabestro.es>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * /tasks/index
 * Pasa al dialogo la url para eliminar la tarea.
 */
$('#confirmTaskDeleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var urlconfirm = button.data('urlconfirm');
    var modal = $(this);
    modal.find('#taskDeleteModal').attr("href", urlconfirm);
});