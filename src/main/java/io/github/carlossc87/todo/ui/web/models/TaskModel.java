/*
 * Copyright (C) 2016 Carlos Serramito Calvo <carlossc87@gmail.com>
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
package io.github.carlossc87.todo.ui.web.models;

import java.io.Serializable;

/**
 * El modelo para las tareas.
 *
 * @author Carlos Serramito Calvo
 */
public class TaskModel implements Serializable {

  /**
   * Version de la clase a serializar.
   */
  static final long serialVersionUID = 1L;

  /**
   * Identificador de la tarea.
   */
  private Integer id;
  
  /**
   * Título de la tarea.
   */
  private String title;

  /**
   * Obtiene el identificador de la tarea.
   *
   * @return Devuelve el identificador de la tarea
   */
  public final Integer getId() {
    return id;
  }

  /**
   * Establece el identificador de la tarea.
   *
   * @param id El identificador de la tarea
   */
  public final void setId(final Integer id) {
    this.id = id;
  }

  /**
   * Obtiene el título de la tarea.
   *
   * @return Devuelve el titulo de la tarea
   */
  public final String getTitle() {
    return title;
  }

  /**
   * Establece el título de la tarea.
   *
   * @param title El título de la tarea
   */
  public final void setTitle(final String title) {
    this.title = title;
  }
}