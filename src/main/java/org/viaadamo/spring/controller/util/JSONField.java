package org.viaadamo.spring.controller.util;

public enum JSONField {
    //EVENT("Event"),
    HOST("Host"),
    MANAGER("Manager"),
    PERSONAL("Personal");

    private String field;

    JSONField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
