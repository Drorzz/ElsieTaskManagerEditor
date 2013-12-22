package org.drorzz.bean;

import org.drorzz.dao.UserDAO;
import org.drorzz.model.AccessLevel;
import org.drorzz.model.User;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 19.12.13
 * Time: 14:31
 */

public class UserBean extends AbstractEditorBean<User,UserDAO>{
    public void init(){
        super.init("users");
    }

    public AccessLevel[] getAccessLevelList(){
        return AccessLevel.values();
    }

    public void saveUser(){
        super.save();
    }
}
