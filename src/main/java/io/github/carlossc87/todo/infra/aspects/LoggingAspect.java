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
package io.github.carlossc87.todo.infra.aspects;

import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * Aspecto para loguear la aplicación.
 *
 * @author Carlos Serramito Calvo
 */
@Component
@Aspect
public class LoggingAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("execution(public * *(..))")
  protected final void anyPublicMethod() {
  }

  @Pointcut("within(@org.springframework.stereotype.Service *)")
  protected final void anyService() {
  }
  
  @Pointcut("within(@org.springframework.stereotype.Controller *)")
  protected final void anyController() {
  }
  
  @Pointcut("execution("
          + "public "
          + "org.springframework.web.servlet.ModelAndView "
          + "io.github.carlossc87.todo.ui.web.controllers."
          + "ExceptionsController.handleNotFound(..)"
          + ")")
  protected final void notFound() {
  }
  
  @Pointcut("execution("
          + "public "
          + "org.springframework.web.servlet.ModelAndView "
          + "io.github.carlossc87.todo.ui.web.controllers."
          + "ExceptionsController.handleError(..)"
          + ")")
  protected final void error() {
  }

  @Before("(anyService() || anyController() ) && anyPublicMethod()")
  public void loggingCallToInfo(JoinPoint joinPoint) {
    final StringBuilder paramsSringBuilder = new StringBuilder();
    final StringBuilder logSringBuilder = new StringBuilder();
    
    final String method = MethodSignature.class.cast(joinPoint.getSignature())
            .getMethod().getName();
    
    String clazz = joinPoint.getTarget().toString();
    if( clazz.contains("@") ){
      clazz = clazz.substring(0, clazz.indexOf("@"));
    }
    
    for (Object param : joinPoint.getArgs()) {
      paramsSringBuilder.append(param).append(" ");
    }
    final String params = paramsSringBuilder.toString();

    logSringBuilder.append("Call to method ").append(method);
    if (!params.isEmpty()) {
      logSringBuilder.append(" with params ").append(params);
    }
    logSringBuilder.append(" in class ").append(clazz);
    
    LOGGER.info(logSringBuilder.toString());
  }
  
  @AfterReturning(pointcut = "notFound()", returning="mav")
  public void loggingNotFound(ModelAndView mav){
    LOGGER.warn("Page not found. Uri: " + mav.getModel().get("uri"));
  }
  
  @AfterReturning(pointcut = "error()", returning="mav")
  public void loggingError(ModelAndView mav){
    final StringBuilder errorStringBuilder = new StringBuilder();
    errorStringBuilder.append("Application error. Id: ");
    errorStringBuilder.append(mav.getModel().get("error"));
    errorStringBuilder.append(", uri: ");
    errorStringBuilder.append(mav.getModel().get("uri"));
    
    LOGGER.error(errorStringBuilder.toString());
  }
}
