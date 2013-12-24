package org.drorzz.dao;

import org.drorzz.model.User;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 09.12.13
 * Time: 17:36
 */
public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO{

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByLogin(String login) {
        return (User) getCurrentSession().bySimpleNaturalId(genericClass).load(login);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<User> getUserLike(String login) {
        return (List<User>)getCurrentSession().createCriteria(User.class).add(
                Restrictions.or(
                        Restrictions.like("login", login, MatchMode.ANYWHERE).ignoreCase(),
                        Restrictions.like("firstName", login, MatchMode.ANYWHERE).ignoreCase(),
                        Restrictions.like("lastName", login, MatchMode.ANYWHERE).ignoreCase()
                )).list();
    }
}
