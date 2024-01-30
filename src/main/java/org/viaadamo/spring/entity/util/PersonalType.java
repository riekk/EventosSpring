package org.viaadamo.spring.entity.util;

public enum PersonalType {
    PL("Policia Local"),
    MU("Mobilidad Urbana"),
    PC("Proteccion Civil"),
    VOL("Voluntario"),
    AYTO("Ayuntamiento");

    private final String displayName;

    PersonalType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
