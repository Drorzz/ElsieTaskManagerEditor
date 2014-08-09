package org.drorzz.elsie.controller;

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

    private final static String mappingEditMask = "[1-9]+|new";

    private final Class<E> entityClass;

    private final String entityListView;
    private final String entityEditView;

    protected S entityService;

    @SuppressWarnings("unchecked")
    public AbstractEntityController(String entityListView, String entityEditView) {
        this.entityClass = (Class<E>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        this.entityListView = entityListView;
        this.entityEditView = entityEditView;
    }

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
        return redirectToList();
    }

    @RequestMapping(value = "/{pathId:"+ mappingEditMask +"}", method = RequestMethod.GET)
    public final String entityByIdMapping(Model model, @PathVariable(value = "pathId") String pathId) {
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

    protected abstract void addEntityByIdMappingModelAttributes(Model model, E entity);

    @RequestMapping(value = "/{pathId:"+ mappingEditMask +"}", method = RequestMethod.POST)
//    public final String entitySaveMapping(@ModelAttribute("entity") E entity) {
    public final String entitySaveMapping(E entity) {
        logger.info("Save {}. Id: {}", getClassName(),entity.getId());
        entityService.save(entity);
        return redirectToList();
    }

    protected String redirectToList(){
        logger.info("Redirect to {} list", getClassName());
        return "redirect:";
    }

    private E createEntity(){
        return entityService.create();
    }
}
