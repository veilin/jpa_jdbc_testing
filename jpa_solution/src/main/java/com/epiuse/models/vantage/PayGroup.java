package com.epiuse.models.vantage;

import com.epiuse.models.IPayGroup;
import com.epiuse.models.PayGroupBase;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pay_group")
public class PayGroup extends PayGroupBase implements IPayGroup {
    long id;

    String name;

    public PayGroup() {
    }

    public PayGroup(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
