package org.drorzz.elsie.dao.impl;

import org.drorzz.elsie.dao.UserDAO;
import org.drorzz.elsie.domain.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13

 * Time: 17:36
 */
@Repository
public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {
    private final static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public User getByLogin(String login) {
        User user = (User) currentSession().bySimpleNaturalId(entityClass).load(login);
        logger.info("Get {} by login, value: {}.", getClassName(), login);
        return user;
    }

    protected Criteria getUserLikeCriteria(String value){
        return currentSession().createCriteria(User.class).add(
                Restrictions.or(
                        Restrictions.like("login", value, MatchMode.ANYWHERE).ignoreCase(),
                        Restrictions.like("firstName", value, MatchMode.ANYWHERE).ignoreCase(),
                        Restrictions.like("lastName", value, MatchMode.ANYWHERE).ignoreCase()
                ));
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUserLike(String value) {
        List<User> list = getUserLikeCriteria(value).list();
        logger.info("Get {} like: {}. Count: {}", getClassName(), value, list.size());
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUserLikeWithOrder(String value, String orderField) {
        List<User> list = getUserLikeCriteria(value).addOrder(Order.asc(orderField)).list();
        logger.info("Get {} like: {}. Order: {}. Count: {}", getClassName(), value, orderField, list.size());
        return list;
    }
}
