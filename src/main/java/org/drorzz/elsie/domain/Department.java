package org.drorzz.elsie.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 06.12.13
 * Time: 12:36
 */
@Entity
@Table(name = "departments")
@AttributeOverride(name="id",column=@Column(name="depart_id"))
public class Department extends PersistentObject implements Serializable {
    private String name;//depart_name
    private int level;//depart_lvl
    private int listOrder;//depart_list_order
    private int projectActive;//depart_proj_active

    private List<User> usersList;

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

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<User> getUsersList() {
        return usersList;
    }
    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }
}
