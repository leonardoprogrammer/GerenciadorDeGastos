package com.example.gerenciadordegastos.enums;

public enum ThemeMode {

    LIGHT_MODE(1, "Light Mode"),
    DARK_MODE(2, "Dark Mode");

    private final int id;
    private final String theme;

    ThemeMode(int id, String theme) {
        this.id = id;
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public static ThemeMode get(int id) {
        for (ThemeMode t : ThemeMode.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
