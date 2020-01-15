package com.epiuse.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Entity
public interface IPayGroup {
    @Id
    long id = 0;

    String name = null;

    String getName();

}
