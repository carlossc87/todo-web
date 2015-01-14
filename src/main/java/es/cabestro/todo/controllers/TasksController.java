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
package es.cabestro.todo.controllers;

import es.cabestro.todo.entities.Task;
import es.cabestro.todo.services.TasksService;
import es.cabestro.todo.validators.TaskValidator;
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
 * Manejador de las tareas.
 * 
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
 */
@Controller
@RequestMapping(value={"/","tasks"})
public class TasksController { 
    
    private static final Logger LOG = LoggerFactory.getLogger(TasksController.class);
    
    private static final String REDIRECT = "redirect:/";
    private static final String TASKS_INDEX = "tasks/index";
    private static final String TASKS_ADD = "tasks/add";
    private static final String TASKS_EDIT = "tasks/edit";
    
    @Autowired
    private TasksService tasksService;
    
    /**
     * Añade los validadores de las entidades.
     * 
     * @param binder El binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new TaskValidator());
    }
    
    /**
     * Lista las tareas.
     * 
     * @param model El modelo de datos recivido de la vista y devuelto a la vista
     * @param locale El idioma del cliente
     * @return Devuelve la vista de las listas
     */
    @RequestMapping(value={"","index"})
    public String index(Model model, Locale locale){
        LOG.debug("Mostrar todas las tareas.");
        model.addAttribute("tasks", tasksService.list());
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
        model.addAttribute("task", new Task());
        return TASKS_ADD;
    }
    
    /**
     * Muestra un formulario para editar una tarea.
     * 
     * @param id El identificador de la tarea
     * @param model El modelo de datos recivido de la vista y devuelto a la vista
     * @return Se muestra el formulario
     */
    @RequestMapping("edit")
    public String edit(Integer id, Model model) {
        LOG.debug("Mostrar el formulario para editar una tarea.");
        model.addAttribute("task", tasksService.find(id));
        return TASKS_EDIT;
    }
    
    /**
     * Guarda una tarea modificada.
     * 
     * @param task La tarea con los cambios
     * @param result El resultado del validador
     * @return Se redirige a la lista de tareas
     */
    @RequestMapping("save")
    public String save(@Valid Task task, BindingResult result) {
        LOG.debug("Guardar los cambios de una tarea nueva o existente.");
        if(result.hasErrors()) {
            return TASKS_EDIT;
        }
        tasksService.save(task);
        return REDIRECT + TASKS_INDEX;
    }
    
    /**
     * Elimina una tarea.
     * 
     * @param id El identificador de la tarea.
     * @return Se redirige a la lista de tareas
     */
    @RequestMapping("delete")
    public String delete(Integer id) {
        LOG.debug("Eliminar una tarea.");
        tasksService.delete(id);
        return REDIRECT + TASKS_INDEX;
    }
}
