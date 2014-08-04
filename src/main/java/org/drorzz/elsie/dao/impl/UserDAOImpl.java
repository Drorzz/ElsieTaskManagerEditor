package org.drorzz.elsie.dao.impl;

import org.drorzz.elsie.dao.UserDAO;
import org.drorzz.elsie.domain.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13

 * Time: 17:36
 */
public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByLogin(String login) {
        return (User) getCurrentSession().bySimpleNaturalId(genericClass).load(login);
    }

    protected Criteria getUserLikeCriteria(String value){
        return getCurrentSession().createCriteria(User.class).add(
                Restrictions.or(
                        Restrictions.like("login", value, MatchMode.ANYWHERE).ignoreCase(),
                        Restrictions.like("firstName", value, MatchMode.ANYWHERE).ignoreCase(),
                        Restrictions.like("lastName", value, MatchMode.ANYWHERE).ignoreCase()
                ));
    }


    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<User> getUserLike(String value) {
        return getUserLikeCriteria(value).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<User> getUserLikeWithOrder(String value, String orderField) {
        return getUserLikeCriteria(value).addOrder(Order.asc(orderField)).list();
    }
}
