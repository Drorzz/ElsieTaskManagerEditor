package org.drorzz.dao;

import org.drorzz.model.User;
import org.hibernate.Query;
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

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<User> getUserLike(String value) {
        Query q = getCurrentSession().createQuery("from User " +
                                                    "where lower(login) like :value "+
                                                    "or lower(firstName) like :value "+
                                                    "or lower(lastName) like :value ");
        q.setString("value", "%"+value.toLowerCase()+"%");
        return q.list();
    }
}
