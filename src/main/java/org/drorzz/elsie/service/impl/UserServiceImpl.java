package org.drorzz.elsie.service.impl;

import org.drorzz.elsie.dao.UserDAO;
import org.drorzz.elsie.domain.User;
import org.drorzz.elsie.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@Service
public class UserServiceImpl extends AbstractEntityServiceImpl<User,UserDAO> implements UserService {

    @Override
    public User getByLogin(String login) {
        return entityDAO.getByLogin(login);
    }

    @Override
    public List<User> getUserLike(String value) {
        return entityDAO.getUserLike(value);
    }

    @Override
    public List<User> getUserLike(String value, String orderField, String orderDirection) {
        return entityDAO.getUserLike(value, orderField, orderDirection);
    }
}
