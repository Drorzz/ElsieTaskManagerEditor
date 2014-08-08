package org.drorzz.elsie.controller.converter;

import org.apache.log4j.Logger;
import org.drorzz.elsie.dao.AbstractDAO;
import org.drorzz.elsie.domain.PersistentObject;
import org.drorzz.elsie.service.AbstractEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Drorzz on 08.08.2014.
 */
public class StringToPersistentObject<T extends PersistentObject, M extends AbstractEntityService<T,? extends AbstractDAO<T>>> implements Converter<String, T> {
    private static final Logger logger = Logger.getLogger(StringToPersistentObject.class);

    private M entityService;
    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public StringToPersistentObject(){
        super();
        entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @Autowired
    public void setEntityService(M entityService){
        this.entityService = entityService;
    }

    @Override
    public T convert(String source) {
        logger.info("Converting(" + entityClass.getSimpleName() + "): ".concat(source));
        try {
            return entityService.getById(Integer.parseInt(source));
        } catch(NumberFormatException nfe){
            throw new RuntimeException(nfe.getMessage());
        }
    }
}
