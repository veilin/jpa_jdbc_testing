package com.epiuse.models;

import javax.persistence.*;

@Entity()
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PayGroupBase implements IPayGroup {

    long id;

    String name;

    public PayGroupBase() {
    }

    public PayGroupBase(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
