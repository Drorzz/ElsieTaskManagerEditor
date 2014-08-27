package org.drorzz.elsie.tme.service;

import org.drorzz.elsie.tme.dao.UserDAO;
import org.drorzz.elsie.tme.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@SuppressWarnings("UnusedDeclaration")
@Transactional(readOnly = true)
public interface UserService extends AbstractEntityService<User,UserDAO>{
    User getByLogin(String login);
    List<User> getUserLike(String value);
    List<User> getUserLike(String value, String orderField, String orderDirection);
}
