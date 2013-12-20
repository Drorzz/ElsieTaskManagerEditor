package org.drorzz.model;

import org.hibernate.Hibernate;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 20.12.13
 * Time: 10:25
 */
@MappedSuperclass
public abstract class PersistentObject {
    private Integer id;

    @Override
    public String toString() {
        if (getId() != null) {
            return Long.toString(getId());
        } else {
            return "";
        }
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() == null ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!Hibernate.getClass(other).equals(Hibernate.getClass(this))) {
            return false;
        }

        return getId().equals(((PersistentObject) other).getId());
    }

    @Transient
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
