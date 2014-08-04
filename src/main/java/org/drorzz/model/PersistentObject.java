package org.drorzz.model;

import javax.persistence.MappedSuperclass;
import org.hibernate.Hibernate;

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
        return (this == other) || (other != null) && getClass() == other.getClass() &&
                Hibernate.getClass(other).equals(Hibernate.getClass(this)) &&
                id.equals(((PersistentObject) other).id);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
