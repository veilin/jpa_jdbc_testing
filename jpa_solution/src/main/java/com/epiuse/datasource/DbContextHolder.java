package com.epiuse.datasource;

public class DbContextHolder {

    private static final ThreadLocal<DBDetails> contextHolder =
            new ThreadLocal<DBDetails>();

    public static void setDbType(DBDetails dbType) {
        if (dbType == null) {
            throw new NullPointerException();
        }
        contextHolder.set(dbType);
    }

    public static DBDetails getDbType() {
        if (contextHolder.get() == null) {
            return new DBDetails("com.epiuse.models.vantage", "vantage"); //Return a default for now
        }
        return contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
