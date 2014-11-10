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

import es.cabestro.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value={"/","todo"})
public class TodoController { 
    
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);
    
    @Autowired
    private TodoService todoService;
    
    @RequestMapping(value={"","add"})
    public String add(@RequestParam(value="text", required=true) String text, Model model) {
        //model.addAttribute("texto", texto);
        
        log.debug("TodoController add");
        
        todoService.add(text);
        
        log.debug("TodoController add fin");
        
        
        return "todo/add";
    }
    
    

}
