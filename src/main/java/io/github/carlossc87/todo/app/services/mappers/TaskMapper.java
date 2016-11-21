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
package io.github.carlossc87.todo.app.services.mappers;

import io.github.carlossc87.todo.app.domain.Task;
import io.github.carlossc87.todo.app.services.dtos.TaskDto;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * Mapea objetos de la clase Task a otras clases.
 * @author Carlos Serramito Calvo
 */
@Mapper(componentModel = "spring")
public interface TaskMapper {

  /**
   * Mapea un objeto Task a TaskDto.
   * @param task El objeto de la clase Task a mapear
   * @return El objeto de la clase TaskDto mapeada
   */
  TaskDto taskToTaskDto(Task task);
  
  /**
   * Mapea un lista de Task a una lista de TaskDto.
   * @param task La lista de la clase Task a mapear
   * @return Lista de la clase TaskDto resultante mapeada
   */
  List<TaskDto> tasksToTaskDtos(List<Task> task);

  /**
   * Mapea un objeto TaskDto a Task.
   * @param taskDto El objeto de la clase TaskDto a mapear
   * @return El objeto de la clase Task mapeada
   */
  Task taskDtoToTask(TaskDto taskDto);
}
