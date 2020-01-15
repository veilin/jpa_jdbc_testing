package com.epiuse.models.ev6;

import com.epiuse.models.IPayGroup;
import com.epiuse.models.PayGroupBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "pay_group")
public class PayGroup extends PayGroupBase implements IPayGroup {

    public PayGroup() {
    }

    public PayGroup(long id, String name) {
        super(id, name);
    }

}
