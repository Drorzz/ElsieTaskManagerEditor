package org.drorzz.elsie.tme.dao.impl;

import org.drorzz.elsie.tme.dao.UserDAO;
import org.drorzz.elsie.tme.domain.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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

    @Override
    public User getByLogin(String login) {
        return (User) currentSession().bySimpleNaturalId(entityClass).load(login);
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
        return getUserLikeCriteria(value).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUserLike(String value,String orderField,String orderDirection) {
        return getUserLikeCriteria(value).addOrder(getOrder(orderField,orderDirection)).list();
    }
}
