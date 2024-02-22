package com.atto.attoproject.domain.enums;

public enum Alive {
    Enabled("활성화"),
    Disabled("비활성화");

    private String description;

    Alive(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
