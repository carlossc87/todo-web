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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
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
 * @author Carlos Serramito Calvo
 */
@ControllerAdvice
public class ExceptionsController {

  /**
   * Captura las excepciones cuando no se encuentra una página.
   *
   * @param request Petición que produce que se lance la excepción
   * @return Muestra una página de error
   */
  @ExceptionHandler(value = {NoHandlerFoundException.class,
    MissingServletRequestParameterException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ModelAndView handleNotFound(
          final HttpServletRequest request) {
    // Mostramos una página con el error
    final Map<String, Object> model = new HashMap<>();
    model.put("uri", request.getRequestURI());
    return new ModelAndView("exceptions/notfound", model);
  }

  /**
   * Captura las excepciones que se producen en la aplicación y no son tratadas.
   *
   * @param request Petición que produce que se lance la excepción
   * @param exception La excepción que se produce
   * @return Muestra una página de eroor
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ModelAndView handleError(final HttpServletRequest request,
          final Exception exception) {
    // Mostramos una página con el error
    final Map<String, Object> model = new HashMap<>();
    model.put("error", UUID.randomUUID().toString());
    model.put("uri", request.getRequestURI());
    return new ModelAndView("exceptions/error", model);
  }
}
