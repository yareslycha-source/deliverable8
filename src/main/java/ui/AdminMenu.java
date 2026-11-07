package org.example.ui;

import org.example.dao.ActivityLogDao;
import org.example.model.ActivityLog;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private final ActivityLogDao activityLogDao = new ActivityLogDao();

    public AdminMenu() {}

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== CLINIC SUPPLY & RESTOCK SYSTEM ===");
            System.out.println("1. Supply Inventory Management");
            System.out.println("2. View System Audit Logs");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("-> Supply Inventory Management selected.");
                    // Test log action for demonstration
                    activityLogDao.logAction(1, "SUPPLY_RESTOCKED", "Admin 1 accessed inventory management.");
                    break;
                case 2:
                    showAuditLogsMenu(scanner);
                    break;
                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);
    }

    private void showAuditLogsMenu(Scanner scanner) {
        System.out.println("\n--- Audit Logs Menu ---");
        System.out.println("1. View All System Logs");
        System.out.println("2. Filter Logs by User ID");
        System.out.print("Choose filter option: ");

        int subChoice = scanner.nextInt();
        scanner.nextLine();

        List<ActivityLog> logs;
        if (subChoice == 2) {
            System.out.print("Enter User ID to filter: ");
            int targetUserId = scanner.nextInt();
            scanner.nextLine();
            logs = activityLogDao.getLogsByUserId(targetUserId);
        } else {
            logs = activityLogDao.getAllLogs();
        }

        printFormattedLogs(logs);
    }

    private void printFormattedLogs(List<ActivityLog> logs) {
        String line = "------------------------------------------------------------------------------------------------";
        System.out.println("\n" + line);
        System.out.printf("%-5s | %-8s | %-18s | %-35s | %-20s%n", "ID", "User ID", "Action Type", "Description", "Timestamp");
        System.out.println(line);

        if (logs.isEmpty()) {
            System.out.println("No activity logs found.");
        } else {
            for (ActivityLog log : logs) {
                System.out.printf("%-5d | %-8d | %-18s | %-35s | %-20s%n",
                        log.getId(),
                        log.getUserId(),
                        log.getActionType(),
                        log.getDescription(),
                        log.getTimestamp()
                );
            }
        }
        System.out.println(line);
    }
}