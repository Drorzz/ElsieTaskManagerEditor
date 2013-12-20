package org.drorzz.bean;

import org.drorzz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.drorzz.dao.UserDAO;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 10.12.13
 * Time: 9:55
 */
@ManagedBean
@ViewScoped
public class UsersBean implements Serializable{
    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUsers(){
        return userDAO.getAll();
    }
}
