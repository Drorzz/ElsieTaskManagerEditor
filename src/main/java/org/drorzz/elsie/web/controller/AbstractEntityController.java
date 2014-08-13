package org.drorzz.elsie.web.controller;

import org.drorzz.elsie.domain.PersistentObject;
import org.drorzz.elsie.service.AbstractEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Drorzz on 09.08.2014.
 */
@SessionAttributes("entity")
public abstract class AbstractEntityController<E extends PersistentObject, S extends AbstractEntityService<E,?>> {
    private final static Logger logger = LoggerFactory.getLogger(AbstractEntityController.class);

    private final static String mappingEditMask = "[0-9]+|new";
    private final static String mappingDeleteMask = "[0-9]+";

    private final Class<E> entityClass;

    private final String entityListMapping;
    private final String entityListView;
    private final String entityEditView;

    protected S entityService;

    @SuppressWarnings("unchecked")
    public AbstractEntityController(String entityListView, String entityEditView) {
        this.entityClass = (Class<E>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        entityListMapping = getClassRequestMappingValue();

        this.entityListView = entityListView;
        this.entityEditView = entityEditView;
    }

    private String getClassRequestMappingValue(){
        RequestMapping requestMapping = getClass().getAnnotation(RequestMapping.class);
        if (requestMapping == null) return "";
        String[] values = requestMapping.value();
        return values.length == 1 ? values[0] : "";
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setEntityService(S entityService) {
        this.entityService = entityService;
    }

    protected String getClassName(){
        return entityClass.getSimpleName();
    }

    protected List<E> entityList(){
        List<E> departmentList = entityService.getAll();
        logger.info("{} list size: {}.", getClassName(), departmentList.size());
        return departmentList;
    }

    @RequestMapping(method = RequestMethod.GET)
    public final String entityListMapping(Model model) {
        model.addAttribute("entityList", entityList());
        addEntityListMappingModelAttributes(model);
        return entityListView;
    }

    protected abstract void addEntityListMappingModelAttributes(Model model);

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public final String wrongEntityMapping() {
//        logger.warn("Wrong {} url: {}", getClassName(), pathId);
        return redirectToList();
    }

    @RequestMapping(value = "/{pathId:"+ mappingEditMask +"}", method = RequestMethod.GET)
    public final String entityByIdMapping(Model model, @PathVariable String pathId) {
        E entity;
        if (pathId.toLowerCase().equals("new")){
            entity = createEntity();
        }else{
            try{
                Integer intId = Integer.valueOf(pathId);
                entity = entityService.getById(intId);
            }catch(NumberFormatException e){
                return redirectToList();
            }
        }
        if(entity == null){
            return redirectToList();
        }
        logger.info("{} id: {}.", getClassName(), entity.getId());

        model.addAttribute("entity", entity);
        addEntityByIdMappingModelAttributes(model, entity);

        return entityEditView;
    }

    @RequestMapping(value = "/{pathId:"+ mappingDeleteMask +"}", params = "delete",method = RequestMethod.GET)
    public final String deleteEntityByIdMapping(@PathVariable String pathId) {
        logger.info("Delete {}. Id: {}", getClassName(),pathId);
        try {
            entityService.deleteById(Integer.valueOf(pathId));
        }catch (NumberFormatException e){
            logger.error("NumberFormatException. Delete {}. Id: {}", getClassName(),pathId);
        }
        return redirectToList();
    }


    protected abstract void addEntityByIdMappingModelAttributes(Model model, E entity);

    @RequestMapping(value = "/{pathId:"+ mappingEditMask +"}", method = RequestMethod.POST)
    public final String entitySaveMapping(E entity) {
        logger.info("Save {}. Id: {}", getClassName(),entity.getId());
        entityService.save(entity);
        return redirectToList();
    }

    protected String redirectToList(){
        logger.info("Redirect to {} list", getClassName());
        return "redirect:"+entityListMapping;
    }

    private E createEntity(){
        return entityService.create();
    }
}
