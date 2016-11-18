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
package io.github.carlossc87.todo.app.services;

import io.github.carlossc87.todo.app.domain.Task;
import io.github.carlossc87.todo.app.services.dtos.TaskDto;
import io.github.carlossc87.todo.app.repositories.TasksRepository;
import io.github.carlossc87.todo.app.services.mappers.TaskMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

/**
 * Servicios para las tareas.
 * 
 * @author Carlos Serramito Calvo
 */
@Service("tasksService")
public class TasksService {

  private static final Logger LOG = LoggerFactory.getLogger(TasksService.class);

  @Autowired
  private TasksRepository tasksRepository;

  @Autowired
  private TaskMapper taskMapper;

  /**
   * Obtiene todas las tareas.
   * 
   * @return La lista de las tareas
   */
  public List<TaskDto> list() {
    LOG.debug("Busca todas las tareas.");
    List<Task> tasks = tasksRepository.findAll();
    return taskMapper.tasksToTaskDtos(tasks);
  }

  /**
   * Obtiene la tarea con el id especificado.
   * 
   * @param id El id de la tarea
   * @return La tarea con el id o null si no existe
   */
  public TaskDto find(Integer id) {
    LOG.debug("Busca una tarea en concreto por el id.");
    Task task = tasksRepository.findOne(id);
    return taskMapper.taskToTaskDto(task);
  }

  /**
   * Guarda la tarea.
   * 
   * @param taskDto La tarea a guardar
   */
  public void save(TaskDto taskDto) {
    LOG.debug("Guarda una nueva tarea o una existente.");

    // Eliminamos los espacios al comienzo y al final.
    taskDto.setTitle(taskDto.getTitle().trim());

    // Eliminamos los caracteres especiales de html.
    taskDto.setTitle(HtmlUtils.htmlEscapeDecimal(taskDto.getTitle()));

    Task task = taskMapper.taskDtoToTask(taskDto);
    tasksRepository.save(task);
  }

  /**
   * Elimina la tarea del id especificado.
   * 
   * @param id El id de la tarea a eliminar.
   */
  public void delete(Integer id) {
    LOG.debug("Elimina una tarea.");
    tasksRepository.delete(id);
  }
}
