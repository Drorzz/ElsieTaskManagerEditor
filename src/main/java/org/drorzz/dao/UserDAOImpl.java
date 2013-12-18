package org.drorzz.dao;

import org.drorzz.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 17:36
 */
public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO{

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User getByLogin(String login) {
        return (User) getCurrentSession().bySimpleNaturalId(genericClass).load(login);
    }
}
