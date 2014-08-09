package org.drorzz.elsie.service.impl;

import org.drorzz.elsie.dao.UserDAO;
import org.drorzz.elsie.domain.User;
import org.drorzz.elsie.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@Service
public class UserServiceImpl extends AbstractEntityServiceImpl<User,UserDAO> implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getByLogin(String login) {
        User user = entityDAO.getByLogin(login);
        logger.info("Get {} by login, value: {}.", getClassName(), login);
        return user;
    }

    @Override
    public List<User> getUserLike(String value) {
        List<User> list = entityDAO.getUserLike(value);
        logger.info("Get {} like: {}. Count: {}", getClassName(), value, list.size());
        return list;
    }

    @Override
    public List<User> getUserLikeWithOrder(String value, String orderField) {
        List<User> list = entityDAO.getUserLikeWithOrder(value,orderField);
        logger.info("Get {} like: {}. Order: {}. Count: {}", getClassName(), value, orderField, list.size());
        return list;
    }
}
