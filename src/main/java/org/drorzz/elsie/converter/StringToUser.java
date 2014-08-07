package org.drorzz.elsie.converter;

import org.apache.log4j.Logger;
import org.drorzz.elsie.dao.UserDAO;
import org.drorzz.elsie.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Drorzz on 07.08.2014.
 */
public class StringToUser implements Converter<String, User> {
    private static final Logger logger = Logger.getLogger(StringToUser.class);

    @Autowired
    UserDAO userDAO;

    @Override
    public User convert(String source) {
        logger.info("Converting: ".concat(source));
        try {
            return userDAO.getById(Integer.parseInt(source));
        } catch(NumberFormatException nfe){
            throw new RuntimeException(nfe.getMessage());
        }
    }
}
