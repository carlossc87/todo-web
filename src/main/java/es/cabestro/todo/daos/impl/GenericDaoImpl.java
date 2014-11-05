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
package es.cabestro.todo.daos.impl;

import es.cabestro.todo.daos.GenericDao;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Serramito Calvo <carlos@cabestro.es>
 * @param <T>
 * @param <ID>
 */
public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    private static final Logger log = LoggerFactory.getLogger(GenericDaoImpl.class);

    private Class<T> getEntityClass(){
        log.debug("GenericDaoImpl getEntityClass");
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        return (Class) pt.getActualTypeArguments()[0];
    }
    
    abstract EntityManager getEntityManager();
    
    private void closeEntityManager(EntityManager em) {
        if( em != null ){
            em.close();
        }
    }

    @Transactional
    @Override
    public void create(T t) {
        log.debug("GenericDaoImpl create");
        EntityManager em = getEntityManager();
        try {
            em.persist(t);
            //em.flush();
            log.debug("post em.persist");
        } finally {
            closeEntityManager(em);
        }
        log.debug("GenericDaoImpl create fin");
    }
    
    @Override
    public void edit(T t){
        EntityManager em = getEntityManager();
        try{
            t = em.merge(t);
        } finally {
            closeEntityManager(em);
        }
    }
    
    @Override
    public void destroy(T t){
        EntityManager em = getEntityManager();
        try{
            em.remove(t);
        } finally {
            closeEntityManager(em);
        }
    }
    
    @Override
    public T find(ID id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(getEntityClass(), id);
        } finally {
            closeEntityManager(em);
        }
    }
    
    @Override
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(getEntityClass()));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            closeEntityManager(em);
        }
    }
    
    @Override
    public List<T> findAll(int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(getEntityClass()));
            Query q = em.createQuery(cq);
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
            return q.getResultList();
        } finally {
            closeEntityManager(em);
        }
    }
    
    @Override
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(getEntityClass());
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            closeEntityManager(em);
        }
    }
}
