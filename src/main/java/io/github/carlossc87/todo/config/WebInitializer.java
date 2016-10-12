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
package io.github.carlossc87.todo.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Inicializador de la aplicación web.
 * 
 * @author Carlos Serramito Calvo <carlossc87@gmail.com>
 */
public class WebInitializer implements WebApplicationInitializer {

    /**
     * Se llama al iniciar la aplicación web.
     * 
     * @param servletContext Contexto incial de la aplicación
     * @throws ServletException Excepciones producida al iniciar el servlet de Spring
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Clase con la configuración de la aplicación
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(Config.class); 
        ctx.setServletContext(servletContext); 

        servletContext.setInitParameter("defaultHtmlEscape", "false");
        
        // Filtro para la codificación
        FilterRegistration.Dynamic fr = servletContext.addFilter(
                "encodingFilter", new CharacterEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");
        
        // Servlet inicial de Spring
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
        // Capturar las excepciones 404
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true); 
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
        servlet.addMapping("/"); 
        servlet.setLoadOnStartup(1); 
        
        
        
    }
}
