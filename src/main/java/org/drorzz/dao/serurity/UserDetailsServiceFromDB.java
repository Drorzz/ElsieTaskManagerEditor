package org.drorzz.dao.serurity;

import org.drorzz.dao.UserDAO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 25.12.13
 * Time: 16:35
 */
public interface UserDetailsServiceFromDB extends UserDetailsService {
    public void setUserDAO(UserDAO userDAO);
}
