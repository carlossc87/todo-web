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
package io.github.carlossc87.todo.services.impl;

import io.github.carlossc87.todo.repositories.TasksRepository;
import io.github.carlossc87.todo.entities.Task;
import io.github.carlossc87.todo.services.TasksService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author Carlos Serramito Calvo <carlossc87@gmail.com>
 */
@Service("tasksService")
public class TasksServiceImpl implements TasksService {

    private static final Logger LOG = LoggerFactory.getLogger(TasksServiceImpl.class);
    
    @Autowired
    private TasksRepository tasksRepository;
    
    @Override
    public List<Task> list(){
        LOG.debug("Busca todas las tareas.");
        return tasksRepository.findAll();
    }
    
    @Override
    public Task find(Integer id){
        LOG.debug("Busca una tarea en concreto por el id.");
        return tasksRepository.findOne(id);
    }
 
    @Override
    public void save(Task todo){
        LOG.debug("Guarda una nueva tarea o una existente.");
        
        // Eliminamos los espacios al comienzo y al final.
        todo.setTitle(todo.getTitle().trim());
        
        // Eliminamos los caracteres especiales de html.
        todo.setTitle(HtmlUtils.htmlEscapeDecimal(todo.getTitle()));
        
        tasksRepository.save(todo);
    }
    
    @Override
    public void delete(Integer id){
        LOG.debug("Elimina una tarea.");
        tasksRepository.delete(id);
    }
}
