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

import es.cabestro.todo.entities.Todo;
import es.cabestro.todo.services.TodoService;
import es.cabestro.todo.validators.TodoValidator;
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

@Controller
@RequestMapping(value={"/","todo"})
public class TodoController { 
    
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new TodoValidator());
    }
    
    @Autowired
    private TodoService todoService;
    
    @RequestMapping(value={"","list"})
    public String list(Model model){
        model.addAttribute("todos", todoService.list());
        return "todo/list";
    }
    
    @RequestMapping("new")
    public String _new(Model model) {
        model.addAttribute("todo", new Todo());
        return "todo/new";
    }
    
    @RequestMapping("add") 
    public String add(@Valid Todo todo, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "todo/new";
        }
        todoService.add(todo);
        return "redirect:/todo/list";
    }
    
    @RequestMapping("edit")
    public String edit(Integer id, Model model) {
        model.addAttribute("todo", todoService.find(id));
        return "todo/edit";
    }
    
    @RequestMapping("save")
    public String save(@Valid Todo todo, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "todo/edit";
        }
        todoService.save(todo);
        return "redirect:/todo/list";
    }
    
    @RequestMapping("delete")
    public String delete(Integer id, Model model) {
        todoService.delete(id);
        return "redirect:/todo/list";
    }
}
