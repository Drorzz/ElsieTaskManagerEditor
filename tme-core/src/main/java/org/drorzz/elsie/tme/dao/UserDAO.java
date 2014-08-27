package org.drorzz.elsie.tme.dao;

import org.drorzz.elsie.tme.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 17:31
 */
public interface UserDAO extends AbstractDAO<User> {
    User getByLogin(String login);
    List<User> getUserLike(String value);
    List<User> getUserLike(String value, String orderField, String orderDirection);
}
