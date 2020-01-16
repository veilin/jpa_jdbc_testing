package com.epiuse.models;

import javax.persistence.*;

public interface IPayGroup {

    @Id
    long getId();

    void setId(long id);

    void setName(String name);

    String getName();

}
