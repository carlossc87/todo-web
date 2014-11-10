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
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@EnableJpaRepositories("es.cabestro.todo.repositories")
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
public class Config {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    /**
     * Configura el resolvedor de las vistas.
     * 
     * @return Devuelve el resolvedor
     */
    @Bean(name = "viewResolver")
    public ViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/classes/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
    
    /**
     * Configura el datasource a la base de datos.
     * 
     * @param driver La clase de driver para realizar la conexion.
     * @param url La url para realizar la conexion
     * @param username El nombre de usuario
     * @param password La contraseña del usuario
     * @return Devuelve el datasource
     */
    @Bean(name = "dataSource")
    public DataSource setupDataSource(
            @Value("${database.todo.basic.driver}") String driver,
            @Value("${database.todo.basic.url}") String url,
            @Value("${database.todo.basic.username}") String username,
            @Value("${database.todo.basic.password}") String password ) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
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
        bean.setPackagesToScan("es.cabestro.todo.entities");
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
