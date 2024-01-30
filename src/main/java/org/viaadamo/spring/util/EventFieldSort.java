package org.viaadamo.spring.util;

public enum EventFieldSort {

    ID("id"),
    NAME("name"),
    DATE("date"),
    FENCES("fences"),
    SIGNALS("signals");

    private String field;

    EventFieldSort(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
