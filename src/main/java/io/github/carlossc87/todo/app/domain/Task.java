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
package io.github.carlossc87.todo.app.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * La entidad para las tareas.
 *
 * @author Carlos Serramito Calvo
 */
@Entity
public class Task implements Serializable {

  /**
   * Version de la clase a serializar.
   */
  static final long serialVersionUID = 1L;

  /**
   * Identificador de la tarea.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  public Integer getId() {
    return id;
  }

  /**
   * Establece el identificador de la tarea.
   *
   * @param value El identificador de la tarea
   */
  public void setId(final Integer value) {
    this.id = value;
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
   * @param value El título de la tarea
   */
  public void setTitle(final String value) {
    this.title = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Task) {
      final Task other = (Task) obj;
      return new EqualsBuilder()
              .append(id, other.id)
              .isEquals();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
            .append(id)
            .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("title", title)
            .toString();
  }
}
