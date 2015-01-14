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

import es.cabestro.todo.exceptions.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Controlador de las excepciones globales (errores HTTP 404 y 505).
 *
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
 */
@ControllerAdvice
public class ExceptionsController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionsController.class);
    
    /**
     * Captura las excepciones cuando no se encuentra una página.
     *
     * @param request Petición que produce que se lance la excepción
     * @return Muestra una página de error
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class, NotFoundException.class, MissingServletRequestParameterException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFoundException(HttpServletRequest request) {
        LOG.debug("Error por no encontrar una página o recurso.");
        
        // Escribimos una alerta en el log
        LOG.warn("Page not found: " + request.getRequestURI());
        
        // Mostramos una página con el error
        Map<String, Object> model = new HashMap<>();
        model.put("uri", request.getRequestURI());
        return new ModelAndView("exceptions/notfound", model);
    }

    /**
     * Captura las excepciones que se producen en la aplicación y no son
     * tratadas.
     *
     * @param request Petición que produce que se lance la excepción
     * @param exception La excepción que se produce
     * @return Muestra una página de eroor
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleError(HttpServletRequest request, Exception exception) {
        LOG.debug("Error no esperado.");
        
        StringBuilder sbError = new StringBuilder();
        
        // Generamos el identificador del error
        UUID idError = UUID.randomUUID();

        // Contruimos el mensaje de error
        sbError.append("ERROR ID: ");
        sbError.append(idError.toString());
        sbError.append(", REQUEST URI: ");
        sbError.append(request.getRequestURI());
        
        // Escribimos el error en el log
        LOG.error(sbError.toString(), exception);
        
        // Mostramos una página con el error
        Map<String, Object> model = new HashMap<>();
        model.put("error", idError.toString());        
        return new ModelAndView("exceptions/error", model);
    }
}
