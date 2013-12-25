package org.drorzz.bean;

import org.drorzz.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.drorzz.dao.UserDAO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 10.12.13
 * Time: 9:55
 */
@ManagedBean(name="usersBean")
@ViewScoped
public class UsersBean{
    @ManagedProperty("#{userDAO}")
    private UserDAO userDAO;

    private String filterString = "";

    public List<User> getAllUsers(){
        return userDAO.getAllWithOrder("fullName");
    }

    public List<User> getFilteredByName(){
        if(filterString.trim().isEmpty()){
            return getAllUsers();
        }else{
            return userDAO.getUserLikeWithOrder(filterString,"fullName");
        }
    }

    public List<User> getFilteredByActive(){
        return userDAO.getByFieldWithOrder("active",true,"fullName");
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String getFilterString() {
        return filterString;
    }

    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }
}
