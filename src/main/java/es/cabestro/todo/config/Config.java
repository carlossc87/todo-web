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

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Configuración de la aplicación.
 * 
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
 */
@Configuration
@ComponentScan("es.cabestro.todo")
@EnableWebMvc
@EnableTransactionManagement
public class Config {

    /**
     * Configura el resolvedor de las vistas.
     * 
     * @return Devuelve el resolvedor
     */
    @Bean(name = "viewResolver")
    public ViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
    
    /**
     * Configura el datasource a la base de datos.
     * 
     * @return Devuelve el datasource
     */
    @Bean(name = "dataSource")
    public DataSource setupDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        dataSource.setUrl("jdbc:derby://localhost:1527/todo");
        dataSource.setUsername("todo");
        dataSource.setPassword("todo");
        return dataSource;
    }
    
    /**
     * Configura el adaptador de JPA.
     * 
     * @return Devuelve el adaptador
     */
    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter setupJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.DERBY);
        adapter.setDatabasePlatform("org.hibernate.dialect.DerbyTenSevenDialect");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }
    
    /**
     * Configura la factoria de los manejadores de entidad.
     * 
     * @param dataSource
     * @param adapter
     * @return Devuelve la factoria
     */
    @Autowired
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory setupEntityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter adapter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPersistenceUnitName("todo");
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(adapter);
        //bean.setValidationMode(ValidationMode.NONE);
        //bean.setSharedCacheMode(SharedCacheMode.NONE);
        bean.getJpaPropertyMap().put("javax.persistence.schema-generation.database.action", "drop-and-create");
        bean.setPackagesToScan("es.cabestro.todo.models");
        bean.afterPropertiesSet();
        return bean.getObject();
    }
    
    /**
     * Configura el manejador de las transacciones.
     * 
     * @param factory
     * @return Devuelve el manejador
     */
    @Autowired
    @Bean(name = "transactionManager")
    public PlatformTransactionManager setupTransactionManager(EntityManagerFactory factory) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(factory);
        return manager;
    }
}
