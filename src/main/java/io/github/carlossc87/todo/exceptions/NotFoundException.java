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
package io.github.carlossc87.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción producida al no encontrar una página o recurso.
 * 
 * @author Carlos Serramito Calvo <carlossc87@gmail.com>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    
    /**
     * Constructor para especificar que no se encontró un recurso.
     * 
     * @param message El mensaje del recurso no encontrado
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor para especificar que no se encontró un recurso que procede
     * de una excepción.
     * 
     * @param message El mensaje del recurso no encontrado.
     * @param cause La excepción causante de esta excepción.
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
