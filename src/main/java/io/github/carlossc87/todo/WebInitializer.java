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
package io.github.carlossc87.todo;

import java.nio.charset.StandardCharsets;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Configurador de los servlet de la aplicación programaticamente.
 *
 * @author Carlos Serramito Calvo
 */
public class WebInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext)
          throws ServletException {
    WebApplicationContext springContext = setupSpringContext(servletContext);
    setupHtmlEscape(servletContext);
    setupEncoding(servletContext);
    setupSpringServlet(springContext, servletContext);
  }

  /**
   * Configuramos el contexto de Spring para la aplicación web.
   *
   * @param servletContext Contexto de los servlets
   * @return Devuelve el contexto de Spring
   */
  private WebApplicationContext setupSpringContext(
          ServletContext servletContext) {
    AnnotationConfigWebApplicationContext springContext
            = new AnnotationConfigWebApplicationContext();
    springContext.register(AppConfig.class);
    springContext.setServletContext(servletContext);
    return springContext;
  }

  /**
   * Configura si se escapa o no el código html desde los tag de Spring MVC.
   *
   * @param servletContext Contexto de los servlets
   */
  private void setupHtmlEscape(ServletContext servletContext) {
    servletContext.setInitParameter("defaultHtmlEscape",
            Boolean.toString(false));
  }

  /**
   * Configura la codificación de entrada y salida de los servlets.
   *
   * @param servletContext Contexto de los servlets
   */
  private void setupEncoding(ServletContext servletContext) {
    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding(StandardCharsets.UTF_8.displayName());
    filter.setForceEncoding(true);
    servletContext
            .addFilter(filter.getClass().getSimpleName(), filter)
            .addMappingForUrlPatterns(null, true, "/*");
  }

  /**
   * Configura el servlet para Spring.
   *
   * @param springContext Contexto de Spring
   * @param servletContext Contexto de los servlets
   */
  private void setupSpringServlet(WebApplicationContext springContext,
          ServletContext servletContext) {
    DispatcherServlet dispatcherServletSpring
            = new DispatcherServlet(springContext);
    dispatcherServletSpring.
            setThrowExceptionIfNoHandlerFound(true);
    ServletRegistration.Dynamic servlet = servletContext.addServlet(
            dispatcherServletSpring.getClass().getSimpleName(),
            dispatcherServletSpring);
    servlet.addMapping("/");
    servlet.setLoadOnStartup(1);
  }

}
