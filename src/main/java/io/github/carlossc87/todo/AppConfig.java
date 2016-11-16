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
package io.github.carlossc87.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Configuración de la aplicación.
 * 
 * @author Carlos Serramito Calvo <carlossc87@gmail.com>
 */
@Configuration
@ComponentScan
@PropertySource("classpath:app.properties")
@Import({WebConfig.class, DatabaseConfig.class})
public class AppConfig {
    
    /**
     * Genera el configurador para el acceso a los archivos de propiedades.
     * 
     * @return Devuelve el configurador para el acceso a las propiedades
     */
    @Bean(name = "PropertySourcesPlaceholderConfigurer")
    public PropertySourcesPlaceholderConfigurer setupPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
}
