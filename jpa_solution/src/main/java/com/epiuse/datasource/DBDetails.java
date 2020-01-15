package com.epiuse.datasource;

public class DBDetails {

    public DBDetails(String JPAPackage, String name) {
        this.JPAPackage = JPAPackage;
        this.name = name;
    }

    public String getJPAPackage() {
        return JPAPackage;
    }

    public void setJPAPackage(String JPAPackage) {
        this.JPAPackage = JPAPackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String JPAPackage;
    public String name;
}
