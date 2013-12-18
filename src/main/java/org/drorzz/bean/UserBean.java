package org.drorzz.bean;

import org.drorzz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;
import org.drorzz.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 10.12.13
 * Time: 9:55
 */
@ManagedBean
@Scope(value = "session")
public class UserBean {
    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUsers(){
        return userDAO.getAll();
    }
}
