package org.drorzz.bean.convert;

import org.drorzz.dao.UserDAO;
import org.drorzz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 20.12.13
 * Time: 11:13
 */
@Component
@Scope("request")
@FacesConverter(value = "userConverter", forClass = User.class)
public class UserConverter  extends ObjectIdAbstractConverter<User> {
    @Autowired
    private UserDAO userDAO;

    protected UserConverter() {
        super(User.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) throws ConverterException {
        return getAsObject(userDAO,value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) throws ConverterException {
        return getAsString(value);
    }
}
