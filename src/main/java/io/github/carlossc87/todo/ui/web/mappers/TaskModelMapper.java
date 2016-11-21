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
package io.github.carlossc87.todo.ui.web.mappers;

import io.github.carlossc87.todo.app.services.dtos.TaskDto;
import io.github.carlossc87.todo.ui.web.models.TaskModel;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * Mapea objetos de la clase TaskModel a otras clases.
 *
 * @author Carlos Serramito Calvo
 */
@Mapper(componentModel = "spring")
public interface TaskModelMapper {

  /**
   * Mapea un objeto TaskDto a TaskModel.
   *
   * @param taskDto El objeto de la clase TaskDto a mapear
   * @return El objeto de la clase TaskModel mapeada
   */
  TaskModel taskDtoToTaskModel(TaskDto taskDto);

  /**
   * Mapea un lista de TaskDto a una lista de TaskModel.
   *
   * @param taskDtos La lista de la clase TaskDto a mapear
   * @return Lista de la clase TaskModel resultante mapeada
   */
  List<TaskModel> taskDtosToTaskModels(List<TaskDto> taskDtos);

  /**
   * Mapea un objeto TaskModel a TaskDto.
   *
   * @param taskModel El objeto de la clase TaskModel a mapear
   * @return El objeto de la clase TaskDto mapeada
   */
  TaskDto taskModelToTaskDto(TaskModel taskModel);

}
