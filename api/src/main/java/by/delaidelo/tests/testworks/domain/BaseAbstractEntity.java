package by.delaidelo.tests.testworks.domain;

import org.hibernate.Hibernate;

public abstract class BaseAbstractEntity {
    public abstract Long getId();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != Hibernate.getClass(obj)) {
            return false;
        }

        final BaseAbstractEntity other = (BaseAbstractEntity) obj;
        return getId() != null && getId().equals(other.getId());
    }

}
