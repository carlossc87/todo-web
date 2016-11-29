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
package io.github.carlossc87.todo.ui.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation
        .ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation
        .WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Configuración del MVC de Spring.
 *
 * @author Carlos Serramito Calvo
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(setupLocaleChangeInterceptor());
  }

  @Override
  public void addResourceHandlers(
          final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/**")
            .addResourceLocations("/assets/");
    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  /**
   * Configura un interceptor para capturar el idioma de la página.
   *
   * @return Devuelve el interceptor para el idioma
   */
  @Bean(name = "localeInterceptor")
  public LocaleChangeInterceptor setupLocaleChangeInterceptor() {
    final LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName("lang");
    return interceptor;
  }

  /**
   * Configura el resolvedor de los locale.
   *
   * @return Devuelve el resolvedor de los locale
   */
  @Bean(name = "localeResolver")
  public SessionLocaleResolver setupSessionLocaleResolver() {
    final SessionLocaleResolver resolver = new SessionLocaleResolver();
    final Locale locale = new Locale("es", "ES");
    resolver.setDefaultLocale(locale);
    return resolver;
  }

  /**
   * Configura la fuente de los mensajes.
   *
   * @return Devuelve la fuente de los mensajes
   */
  @Bean(name = "messageSource")
  public AbstractMessageSource
          setupReloadableResourceBundleMessageSource() {
    final ResourceBundleMessageSource messageSource
            = new ResourceBundleMessageSource();
    messageSource.setBasenames("messages");
    return messageSource;
  }

  /**
   * Configura el resolvedor de las vistas.
   *
   * @return Devuelve el resolvedor
   */
  @Bean(name = "viewResolver")
  public ViewResolver setupViewResolver() {
    final UrlBasedViewResolver resolver = new InternalResourceViewResolver();
    resolver.setViewClass(JstlView.class);
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jspx");
    return resolver;
  }
}
