/*
 * Copyright (c) 2013. Denis Ivansky. All Rights Reserved. No part of this website may be reproduced without Denis Ivansky's express consent.
 */

package org.drorzz.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drorzz.dao.AbstractDAO;
import org.drorzz.model.PersistentObject;

import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Drorzz
 * Date: 22.12.13
 * Time: 22:14
 */
public abstract class AbstractEditorBean<T extends PersistentObject,K extends AbstractDAO<T>> {
    protected static final Logger LOG = LogManager.getLogger(AbstractEditorBean.class.getName());

    private String id;
    private T entity = null;
    private K dao;

    protected void redirect(String page){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
            LOG.info(String.format("Redirect to %s",page));
        } catch (IOException e) {
            LOG.error(String.format("Cannot redirect to %s",page), e);
        }
    }

    protected void init(String errorRedirect) {
        if (id != null && id.matches("^(new|\\d+)$")) {
            if (id.trim().toLowerCase().equals("new")){
                entity = dao.create();
            } else {
                entity = dao.getById(Integer.valueOf(id));
            }
        }

        if(entity == null){
            redirect(errorRedirect);
        }
    }

    protected void save(){
        dao.save(entity);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public K getDao() {
        return dao;
    }

    public void setDao(K dao) {
        this.dao = dao;
    }
}
