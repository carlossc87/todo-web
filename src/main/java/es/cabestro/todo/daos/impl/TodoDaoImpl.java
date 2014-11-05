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
package es.cabestro.todo.daos.impl;

import es.cabestro.todo.daos.TodoDao;
import es.cabestro.todo.models.Todo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
 */
@Component("todoDao")
public class TodoDaoImpl extends GenericDaoImpl<Todo, Integer> implements TodoDao{
    
    private static final Logger log = LoggerFactory.getLogger(TodoDaoImpl.class);
    
    @PersistenceUnit(name = "todo")
    private EntityManagerFactory todoEmf;
            
    @Override
    protected EntityManager getEntityManager(){
        log.debug("TodoDaoImpl getEntityManager");
        return todoEmf.createEntityManager();
    }
}
