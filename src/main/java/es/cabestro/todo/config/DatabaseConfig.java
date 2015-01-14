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

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("es.cabestro.todo.repositories")
@PropertySource("classpath:database.properties")
public class DatabaseConfig {
    
    /**
     * Genera el configurador para el acceso a los archivos de propiedades.
     * 
     * @return Devuelve el configurador para el acceso a las propiedades
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    /**
     * Configura el datasource a la base de datos.
     * 
     * @param nameJndi Nombre del datasource
     * @return Devuelve el datasource
     */
    @Bean(name = "dataSource")
    public DataSource setupDataSource(
            @Value("${database.jndi}") String nameJndi) {   
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        return dsLookup.getDataSource(nameJndi);
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
    @Resource
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
