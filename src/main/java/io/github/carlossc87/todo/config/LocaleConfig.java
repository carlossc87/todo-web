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

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author Carlos Serramito Calvo <carlossc87@gmail.com>
 */
@Configuration
public class LocaleConfig {
    
    /**
     * Configura un interceptor para capturar el idioma de la página.
     * 
     * @return  Devuelve el interceptor para el idioma
     */
    @Bean(name = "localeChangeInterceptor")
    public static HandlerInterceptor setupLocaleChangeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }
    
    /**
     * Configura el resolvedor de los locale.
     * 
     * @return Devuelve el resolvedor de los locale
     */
    @Bean(name = "localeResolver")
    public static SessionLocaleResolver setupSessionLocaleResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        Locale locale = new Locale("es","ES");
        resolver.setDefaultLocale(locale);
        return resolver;
    }
    
    /**
     * Configura la fuente de los mensajes.
     * 
     * @return Devuelve la fuente de los mensajes
     */
    @Bean(name = "messageSource")
    public static AbstractMessageSource setupReloadableResourceBundleMessageSource(){
        ResourceBundleMessageSource messageSource =  new ResourceBundleMessageSource();
        messageSource.setBasenames("io/github/carlossc87/todo/locales/messages");
        return messageSource;
    }
}
