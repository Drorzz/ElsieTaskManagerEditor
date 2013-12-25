package org.drorzz.dao;

import org.drorzz.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 17:31
 */
public interface UserDAO extends AbstractDAO<User> {
    public User getByLogin(String login);
    public List<User> getUserLike(String value);
    public List<User> getUserLikeWithOrder(String value,String orderField);
}
