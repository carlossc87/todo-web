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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuración de la base de datos.
 *
 * @author Carlos Serramito Calvo
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {DatabaseConfig.class})
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

  /**
   * Configura el datasource a la base de datos.
   *
   * @param nameJndi Nombre del datasource
   * @return Devuelve el datasource
   */
  @Bean(name = "dataSource")
  public final DataSource setupDataSource(
          @Value("${database.jndi}") final String nameJndi) {
    final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
    dsLookup.setResourceRef(true);
    return dsLookup.getDataSource(nameJndi);
  }

  /**
   * Configura el adaptador de JPA.
   *
   * @param dialect El dialecto para comunicarse con la base de datos
   * @param showsql Indica si muestra las sentencias sql
   * @param ddl Indica si crea la estructura de la base de datos si es necesario
   * @return Devuelve el adaptador
   */
  @Bean(name = "jpaVendorAdapter")
  public final JpaVendorAdapter setupJpaVendorAdapter(
          @Value("${hibernate.dialect}") final String dialect,
          @Value("${hibernate.showsql}") final boolean showsql,
          @Value("${hibernate.ddl}") final boolean ddl) {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setDatabasePlatform(dialect);
    adapter.setShowSql(showsql);
    adapter.setGenerateDdl(ddl);
    return adapter;
  }

  /**
   * Configura la factoria de los manejadores de entidad.
   *
   * @param name Nombre de la unidad de persistencia.
   * @param dataSource El datasource
   * @param adapter El adaptador
   * @return Devuelve la factoria
   */
  @Bean(name = "entityManagerFactory")
  public final EntityManagerFactory setupEntityManagerFactoryBean(
          @Value("${app.name}") final String name,
          final DataSource dataSource,
          final JpaVendorAdapter adapter) {
    LocalContainerEntityManagerFactoryBean bean
            = new LocalContainerEntityManagerFactoryBean();
    bean.setPersistenceUnitName(name);
    bean.setDataSource(dataSource);
    bean.setJpaVendorAdapter(adapter);
    bean.setPackagesToScan(getClass().getPackage().getName());
    bean.afterPropertiesSet();
    return bean.getObject();
  }

  /**
   * Configura el manejador de las transacciones.
   *
   * @param entityManagerFactory La factoria del manejador de entidades
   * @return Devuelve el manejador
   */
  @Bean(name = "transactionManager")
  public final PlatformTransactionManager setupTransactionManager(
          final EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager manager = new JpaTransactionManager();
    manager.setEntityManagerFactory(entityManagerFactory);
    return manager;
  }
}
