package org.drorzz.dao.serurity;

import org.drorzz.dao.serurity.impl.UserDetailsImpl;
import org.drorzz.model.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 27.12.13
 * Time: 10:18
 */
public class UserDetailsFabric {
    public static UserDetails getUserDetails(User user) {
        return new UserDetailsImpl(user);
    }
}
