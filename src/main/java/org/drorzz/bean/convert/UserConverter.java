package org.drorzz.bean.convert;

import org.drorzz.dao.UserDAO;
import org.drorzz.model.User;

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
@FacesConverter(value = "userConverter", forClass = User.class)
public class UserConverter  extends ObjectIdAbstractConverter<User,UserDAO> {

    protected UserConverter() {
        super(User.class,"userDAO");
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) throws ConverterException {
        return getAsObject(facesContext,value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) throws ConverterException {
        return getAsString(value);
    }
}
