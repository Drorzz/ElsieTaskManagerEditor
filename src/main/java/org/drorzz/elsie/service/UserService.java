package org.drorzz.elsie.service;

import org.drorzz.elsie.dao.UserDAO;
import org.drorzz.elsie.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Drorzz on 08.08.2014.
 */
@Transactional(readOnly = true)
public interface UserService extends AbstractEntityService<User,UserDAO>{
    User getByLogin(String login);
    List<User> getUserLike(String value);
    List<User> getUserLikeWithOrder(String value, String orderField);
}
