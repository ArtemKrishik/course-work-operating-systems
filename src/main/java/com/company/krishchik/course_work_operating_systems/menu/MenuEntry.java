package com.company.krishchik.course_work_operating_systems.menu;

import java.io.IOException;

public abstract class MenuEntry {
    private String title;

    public MenuEntry(String title) {
        this.title = title;
    }

    public abstract void run() throws IOException;

    public String getTitle() {
        return title;
    }
}
