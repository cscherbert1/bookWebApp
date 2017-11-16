/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.cms.bookwebapp.model;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author cscherbert1
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEm();

    public void create(T entity) {
        getEm().persist(entity);
    }

    public void edit(T entity) {
        getEm().merge(entity);
    }

    public void remove(T entity) {
        getEm().remove(getEm().merge(entity));
    }
    
    //add a remove by Id method here

    public T findById(Object id) {
        return getEm().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEm().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEm().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEm().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEm().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEm().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEm().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEm().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
