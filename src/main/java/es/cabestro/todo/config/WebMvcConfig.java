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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

/**
 *
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    
    /**
     * 
     * 
     * @param registry 
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/commons/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/commons/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/commons/js/");
        registry.addResourceHandler("/theme/css/**").addResourceLocations("/themes/todo/commons/css/");
        registry.addResourceHandler("/theme/img/**").addResourceLocations("/themes/todo/commons/img/");
        registry.addResourceHandler("/theme/js/**").addResourceLocations("/themes/todo/commons/js/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/"); //www.webjars.org
    }
    
    /**
     * Configura el resolvedor de las vistas.
     * 
     * @return Devuelve el resolvedor
     */
    @Bean(name = "viewResolver")
    public ViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/themes/todo/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    /**
     * Configura los tiles.
     * 
     * @return Devuelve el configurador
     */
    @Bean(name = "tilesConfigurer")
    public TilesConfigurer setupTilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions("/themes/todo/tiles-definitions.xml");
        return configurer;
    }
}
