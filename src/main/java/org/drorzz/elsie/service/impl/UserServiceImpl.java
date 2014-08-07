package org.drorzz.elsie.service.impl;

import org.drorzz.elsie.dao.UserDAO;
import org.drorzz.elsie.domain.User;
import org.drorzz.elsie.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@Service
public class UserServiceImpl extends AbstractEntityServiceImpl<User,UserDAO> implements UserService {
    @Override
    @Transactional(readOnly = true)
    public User getByLogin(String login) {
        return entityDAO.getByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserLike(String value) {
        return entityDAO.getUserLike(value);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserLikeWithOrder(String value, String orderField) {
        return entityDAO.getUserLikeWithOrder(value,orderField);
    }
}
