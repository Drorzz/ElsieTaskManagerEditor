package org.drorzz.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Created by Drorzz on 18.08.2014.
 */
public class PropertyUtilsWrapper {
    private final static Logger logger = LoggerFactory.getLogger(PropertyUtilsWrapper.class);

    public static Object getProperty(Object bean, String name){
        try {
            return PropertyUtils.getProperty(bean,name);
        } catch (Exception e) {
            String errorMessage = String.format("Error in %s with property %s",bean.toString(),name);
            logger.error(errorMessage,e);
            throw new RuntimeException(errorMessage,e);
        }
    }

    public static void setProperty(Object bean, String name, Object value){
        try {
            PropertyUtils.setProperty(bean,name,value);
        } catch (Exception e) {
            String errorMessage = String.format("Error in %s with property %s and value %s",
                    bean.toString(),name,value.toString());
            logger.error(errorMessage,e);
            throw new RuntimeException(errorMessage,e);
        }
    }
}
