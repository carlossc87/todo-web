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
package es.cabestro.todo.validators;

import es.cabestro.todo.entities.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * El validador para la entidad task.
 * 
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
 */
public class TaskValidator implements Validator {

    private static final Logger LOG = LoggerFactory.getLogger(TaskValidator.class);
    
    private static final int MAX_SIZE_TITLE = 10;
    
    /**
     * Comprueba si la clase es de tipo tarea.
     * 
     * @param type El tipo de la clase
     * @return Devuelve true si la clase es una tarea o false de lo contrario
     */
    @Override
    public boolean supports(Class<?> type) {
        return Task.class.equals(type);
    }

    /**
     * Valida una tarea.
     * 
     * @param o La clase a validar
     * @param errors La lista de errores que se detecten
     */
    @Override
    public void validate(Object o, Errors errors) {
        LOG.debug("Validar una tarea.");
        Task todo = (Task) o;
        
        LOG.debug("Comprobando si se pasa un título.");
        if( todo.getTitle() == null ){
            errors.rejectValue("title", "validators.task.title.requerido");
        }
        
        LOG.debug("Comprobando si el título es mayor de del limite.");
        if ( todo.getTitle() != null && todo.getTitle().length() < MAX_SIZE_TITLE){
            errors.rejectValue("title", "validators.task.title.min10");
        }
    }
}
