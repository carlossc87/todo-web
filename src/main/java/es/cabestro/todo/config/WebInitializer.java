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
package es.cabestro.todo.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Inicializador de la aplicación web.
 * 
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
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
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(Config.class); 
        ctx.setServletContext(servletContext); 

        DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
        // Capturar las excepciones 404
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true); 
        Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
        servlet.addMapping("/"); 
        servlet.setLoadOnStartup(1); 
    }
}
