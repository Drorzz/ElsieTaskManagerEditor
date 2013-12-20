package org.drorzz.bean;

import org.drorzz.dao.UserDAO;
import org.drorzz.model.AccessLevel;
import org.drorzz.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 19.12.13
 * Time: 14:31
 */
@ManagedBean
@ViewScoped
public class UserBean implements Serializable{

    private String userId;
    private User user;

    @Autowired
    private UserDAO userDAO;

    public void init(){
        if (userId == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }
        if (userId.trim().toLowerCase().equals("new")){
            try {
                user = userDAO.create();
            } catch (IllegalAccessException e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            } catch (InstantiationException e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }
        } else {
            user = userDAO.getById(Integer.valueOf(userId));
            if (user == null) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("user?id=new");
                } catch (IOException e) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
                }
            }
        }
    }

    public AccessLevel[] getAccessLevelList(){
        return AccessLevel.values();
    }

    public void saveUser(){
        userDAO.save(user);
    }

    public User getUser() {
        return user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
