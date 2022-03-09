package xyz.thiennam.employeems.entity;

import org.springframework.lang.Nullable;

public enum GenderEnum {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");

    private final String name;

    GenderEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public static GenderEnum fromName(String name) {
        for (GenderEnum e : GenderEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
