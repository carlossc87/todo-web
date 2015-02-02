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
package es.cabestro.todo.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * La entidad para las tareas.
 * 
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
 */
@Entity
public class Task implements Serializable {
    
    static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Integer id;
    private String title;

    /**
     * Obtiene el identificador de la tarea.
     * 
     * @return Devuelve el identificador de la tarea
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador de la tarea.
     * 
     * @param id El identificador de la tarea
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el título de la tarea.
     * 
     * @return Devuelve el titulo de la tarea
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título de la tarea.
     * 
     * @param title El título de la tarea
     */
    public void setTitle(String title) {
        this.title = title;
    }
}