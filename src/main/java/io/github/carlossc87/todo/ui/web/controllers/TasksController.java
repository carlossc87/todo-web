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
package io.github.carlossc87.todo.ui.web.controllers;

import io.github.carlossc87.todo.app.services.TasksService;
import io.github.carlossc87.todo.app.services.dtos.TaskDto;
import io.github.carlossc87.todo.ui.web.mappers.TaskModelMapper;
import io.github.carlossc87.todo.ui.web.models.TaskModel;
import io.github.carlossc87.todo.ui.web.validators.TaskValidator;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador de las tareas.
 *
 * @author Carlos Serramito Calvo
 */
@Controller
@RequestMapping(value = {"/", "tasks"})
public class TasksController {

  /**
   * Logger de la clase.
   */
  private static final Logger LOG
          = LoggerFactory.getLogger(TasksController.class);

  /**
   * Prefijo para rederigir a unha vista.
   */
  private static final String REDIRECT = "redirect:/";

  /**
   * Vista para mostrar la lista de tareas.
   */
  private static final String TASKS_INDEX = "tasks/index";

  /**
   * Vista para añadir una tarea.
   */
  private static final String TASKS_ADD = "tasks/add";

  /**
   * Vista para editar una tarea.
   */
  private static final String TASKS_EDIT = "tasks/edit";

  /**
   * Servicios de las tareas.
   */
  @Autowired
  private TasksService tasksService;

  /**
   * Mapeador de la clase TaskModel.
   */
  @Autowired
  private TaskModelMapper taskModelMapper;

  /**
   * Añade los validadores de las entidades.
   *
   * @param binder El binder
   */
  @InitBinder
  protected final void initBinder(final WebDataBinder binder) {
    binder.addValidators(new TaskValidator());
  }

  /**
   * Lista las tareas.
   *
   * @param model El modelo de datos recivido de la vista y devuelto a la vista
   * @param locale El idioma del cliente
   * @return Devuelve la vista de las listas
   */
  @RequestMapping(value = {"", "index"})
  public final String index(final Model model, final Locale locale) {
    LOG.error("Mostrar todas las tareas.");
    final List<TaskDto> taskDtos = tasksService.list();
    final List<TaskModel> taskModels
            = taskModelMapper.taskDtosToTaskModels(taskDtos);
    model.addAttribute("tasks", taskModels);
    return TASKS_INDEX;
  }

  /**
   * Muestra el formulario para añadir una nueva tarea.
   *
   * @param model El modelo de datos recivido de la vista y devuelto a la vista
   * @return Devuelve la vista del formularios
   */
  @RequestMapping("add")
  public String add(Model model) {
    LOG.debug("Mostrar el formulario para añadir una tarea.");
    model.addAttribute("task", new TaskModel());
    return TASKS_ADD;
  }

  /**
   * Guarda una tarea nueva.
   *
   * @param taskModel La tarea con los cambios
   * @param result El resultado del validador
   * @return Se redirige a la lista de tareas
   */
  @RequestMapping("saveadd")
  public final String saveadd(@Valid final TaskModel taskModel,
          final BindingResult result) {
    LOG.debug("Guardar los cambios de una tarea nueva o existente.");
    if (result.hasErrors()) {
      return TASKS_ADD;
    }

    final TaskDto taskDto = taskModelMapper.taskModelToTaskDto(taskModel);
    tasksService.save(taskDto);
    return REDIRECT + TASKS_INDEX;
  }

  /**
   * Muestra un formulario para editar una tarea.
   *
   * @param id El identificador de la tarea
   * @param model El modelo de datos recivido de la vista y devuelto a la vista
   * @return Se muestra el formulario
   */
  @RequestMapping("edit")
  public final String edit(final Integer id, final Model model) {
    LOG.debug("Mostrar el formulario para editar una tarea.");
    final TaskDto taskDto = tasksService.find(id);
    final TaskModel taskModel = taskModelMapper.taskDtoToTaskModel(taskDto);
    model.addAttribute("task", taskModel);
    return TASKS_EDIT;
  }

  /**
   * Guarda una tarea modificada.
   *
   * @param taskModel La tarea con los cambios
   * @param result El resultado del validador
   * @return Se redirige a la lista de tareas
   */
  @RequestMapping("saveedit")
  public final String saveedit(@Valid final TaskModel taskModel,
          final BindingResult result) {
    LOG.debug("Guardar los cambios de una tarea nueva o existente.");
    if (result.hasErrors()) {
      return TASKS_EDIT;
    }

    final TaskDto taskDto = taskModelMapper.taskModelToTaskDto(taskModel);
    tasksService.save(taskDto);
    return REDIRECT + TASKS_INDEX;
  }

  /**
   * Elimina una tarea.
   *
   * @param id El identificador de la tarea.
   * @return Se redirige a la lista de tareas
   */
  @RequestMapping("delete")
  public final String delete(final Integer id) {
    LOG.debug("Eliminar una tarea.");
    tasksService.delete(id);
    return REDIRECT + TASKS_INDEX;
  }
}
