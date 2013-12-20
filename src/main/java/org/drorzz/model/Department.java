package org.drorzz.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 06.12.13
 * Time: 12:36
 */
@Entity
@Table(name = "departments")
public class Department extends PersistentObject{
    private Integer id;//depart_id
    private String name;//depart_name
    private int level;//depart_lvl
    private int listOrder;//depart_list_order
    private int projectActive;//depart_proj_active

    @Id
    @Column(name = "depart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "depart_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "depart_lvl")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Column(name = "depart_list_order")
    public int getListOrder() {
        return listOrder;
    }

    public void setListOrder(int listOrder) {
        this.listOrder = listOrder;
    }

    @Column(name = "depart_proj_active")
    public int getProjectActive() {
        return projectActive;
    }

    public void setProjectActive(int projectActive) {
        this.projectActive = projectActive;
    }
}
