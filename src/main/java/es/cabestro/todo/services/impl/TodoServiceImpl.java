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
package es.cabestro.todo.services.impl;

import es.cabestro.todo.repositories.TodoRepository;
import es.cabestro.todo.entities.Todo;
import es.cabestro.todo.services.TodoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
 */
@Service("todoService")
public class TodoServiceImpl implements TodoService {

    private static final Logger log = LoggerFactory.getLogger(TodoServiceImpl.class);
    
    @Autowired
    private TodoRepository todoRepository;
    
    @Override
    public List<Todo> list(){
        return todoRepository.findAll();
    }
    
    @Override
    public Todo find(Integer id){
        return todoRepository.findOne(id);
    }
            
    @Override
    public void add(Todo todo) {
        todoRepository.save(todo);
    }
 
    @Override
    public void save(Todo todo){
        todoRepository.save(todo);
    }
    
    @Override
    public void delete(Integer id){
        todoRepository.delete(id);
    }
}
