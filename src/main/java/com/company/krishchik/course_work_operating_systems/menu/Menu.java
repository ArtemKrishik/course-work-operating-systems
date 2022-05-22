package com.company.krishchik.course_work_operating_systems.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private List entries = new ArrayList();
    private boolean isExit = false;

    public Menu() {
        entries.add(new MenuEntry("0.Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!isExit) {
            printMenu();
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);
                MenuEntry entry = (MenuEntry) entries.get(choice);
                entry.run();
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Ошибка ввода!" + ANSI_RESET);
            }
        }
    }

    private void printMenu() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(ANSI_YELLOW + "!!ВВОДИТЕ ЧИСЛА БЕЗ ПРОБЕЛОВ!!"+ ANSI_RESET);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        MenuEntry menuEntry;
        for (int i = 0; i < entries.size(); i++) {
            menuEntry = (MenuEntry) entries.get(i);
            System.out.println(menuEntry.getTitle());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void addEntry(MenuEntry menuEntry) {
        entries.add(menuEntry);
    }
}
