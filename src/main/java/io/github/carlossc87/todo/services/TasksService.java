/*
 * Copyright (C) 2014 Carlos Serramito Calvo <carlossc87@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.carlossc87.todo.services;

import io.github.carlossc87.todo.entities.Task;
import java.util.List;
import javax.transaction.Transactional;

/**
 * El servicio para la entidad task.
 * 
 * @author Carlos Serramito Calvo <carlossc87@gmail.com>
 */
public interface TasksService {

    /**
     * Obtiene todas las tareas.
     * 
     * @return Devuelve la lista de las tareas
     */
    public List<Task> list();
    
    /**
     * Busca una tarea a partir del identificador.
     * 
     * @param id El identificador de la tarea
     * @return Devuelve la tarea
     */
    public Task find(Integer id);
    
    /**
     * Se modifica una tarea existente.
     * 
     * @param task La tarea con la modificaciones. El identificador debe 
     * coincidir con uno existente
     */
    @Transactional
    public void save(Task task);
    
    /**
     * Se elimina una tarea existente.
     * 
     * @param id El identificador de la tarea a eliminar
     */
    @Transactional
    public void delete(Integer id);
}
