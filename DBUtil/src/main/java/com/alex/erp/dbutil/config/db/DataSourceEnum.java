package com.alex.erp.dbutil.config.db;

public enum DataSourceEnum {
    DB_UM("um"),DB_EVALUATION("eva");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
