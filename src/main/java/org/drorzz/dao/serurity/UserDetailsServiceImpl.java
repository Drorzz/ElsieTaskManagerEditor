package org.drorzz.dao.serurity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drorzz.dao.UserDAO;
import org.drorzz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 25.12.13
 * Time: 15:57
 */
public class UserDetailsServiceImpl implements UserDetailsServiceFromDB {
    protected static final Logger LOG = LogManager.getLogger(UserDetailsServiceImpl.class.getName());

    @Autowired
    private UserDAO userDAO;


    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;
        try {
            user = userDAO.getByLogin(s);
            if (user == null) {
                throw new RuntimeException(String.format("Getting User by login %s is null", s));
            }
        } catch (RuntimeException e) {
            String message = String.format("Cannot get User by login %s", s);
            LOG.error(message, e);
            throw new UsernameNotFoundException(message, e);
        }
        LOG.debug(String.format("Getting User by login %s is null", s));
        return new UserDetailsImpl(user);
    }
}
