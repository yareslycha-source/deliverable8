package org.example;

import org.example.dao.DatabaseInitializer;
import org.example.ui.AdminMenu;

public class Main {
    public static void main(String[] args) {
        // Initialize database tables on startup (creates activity_logs automatically)
        DatabaseInitializer.initializeDatabase();

        // Launch the application menu
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.start();
    }
}