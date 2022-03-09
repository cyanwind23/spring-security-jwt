package xyz.thiennam.employeems.entity;

import org.springframework.lang.Nullable;

public enum RoleNameEnum {
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER"),
    USER("ROLE_USER");

    private final String name;

    RoleNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public static RoleNameEnum fromName(String name) {
        for (RoleNameEnum e : RoleNameEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}

