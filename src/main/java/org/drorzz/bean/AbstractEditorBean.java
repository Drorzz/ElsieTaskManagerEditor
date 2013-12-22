/*
 * Copyright (c) 2013. Denis Ivansky. All Rights Reserved. No part of this website may be reproduced without Denis Ivansky's express consent.
 */

package org.drorzz.bean;

import org.drorzz.dao.AbstractDAO;
import org.drorzz.model.PersistentObject;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Drorzz
 * Date: 22.12.13
 * Time: 22:14
 */
public abstract class AbstractEditorBean<T extends PersistentObject,K extends AbstractDAO<T>> {
    private String id;
    private T entity = null;
    private K dao;

    protected void redirect(String page){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        } catch (IOException e) {
            sendErrorMessage(e);
        }
    }

    protected void sendErrorMessage(Exception e){
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
    }

    protected void init(String errorRedirect) {
        if (id != null && id.matches("^(new|\\d+)$")) {
            if (id.trim().toLowerCase().equals("new")){
                try {
                    entity = dao.create();
                } catch (IllegalAccessException e) {
                    sendErrorMessage(e);
                } catch (InstantiationException e) {
                    sendErrorMessage(e);
                }
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
        dao.refresh(entity);
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
