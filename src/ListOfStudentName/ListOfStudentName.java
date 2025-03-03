package ListOfStudentName;

import java.util.*;

public class ListOfStudentName {
    private static final Map<Integer, String> studentMap = new HashMap<>();
    private static final List<String> history = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> scanner.close()));

        while (!login()) {
        }

        while (true) {
            System.out.println("\n===== Student CRUD Operations =====");
            System.out.println("1️⃣  Create Student");
            System.out.println("2️⃣  Read Students");
            System.out.println("3️⃣  Update Student");
            System.out.println("4️⃣  Delete Student");
            System.out.println("5️⃣  View History");
            System.out.println("6️⃣  Exit");
            System.out.print("👉 Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> createStudent();
                    case 2 -> readStudents();
                    case 3 -> updateStudent();
                    case 4 -> deleteStudent();
                    case 5 -> viewHistory();
                    case 6 -> {
                        System.out.println("👋 Exiting program...");
                        return;
                    }
                    default -> System.out.println("❌ Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void createStudent() {
        try {
            System.out.print("🆔 Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (studentMap.containsKey(id)) {
                System.out.println("❌ Error: Student ID already exists!");
                return;
            }

            System.out.print("📝 Enter Student Name: ");
            String name = scanner.nextLine();
            studentMap.put(id, name);
            history.add("✅ Created Student - ID: " + id + ", Name: " + name);
            System.out.println("🎉 Student added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("⚠️ Invalid input! Student ID must be a number.");
            scanner.nextLine();
        }
    }

    private static void readStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("📭 No students found.");
            return;
        }

        System.out.println("\n📋 ===== Student List =====");
        studentMap.forEach((id, name) -> System.out.println("🆔 ID: " + id + ", Name: " + name));
    }

    private static void updateStudent() {
        try {
            System.out.print("✏️ Enter Student ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (!studentMap.containsKey(id)) {
                System.out.println("❌ Error: Student ID not found!");
                return;
            }

            System.out.print("🔄 Enter new Student Name: ");
            String newName = scanner.nextLine();
            String oldName = studentMap.get(id);
            studentMap.put(id, newName);
            history.add("🔄 Updated Student - ID: " + id + " (Old Name: " + oldName + ", New Name: " + newName + ")");
            System.out.println("✅ Student updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("⚠️ Invalid input! Student ID must be a number.");
            scanner.nextLine();
        }
    }

    private static void deleteStudent() {
        try {
            System.out.print("🗑️ Enter Student ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (!studentMap.containsKey(id)) {
                System.out.println("❌ Error: Student ID not found!");
                return;
            }

            String name = studentMap.remove(id);
            history.add("🗑️ Deleted Student - ID: " + id + ", Name: " + name);
            System.out.println("✅ Student deleted successfully.");
        } catch (InputMismatchException e) {
            System.out.println("⚠️ Invalid input! Student ID must be a number.");
            scanner.nextLine();
        }
    }

    private static boolean login() {
        Map<String, String> users = new HashMap<>();
        users.put("admin", "admin123");

        System.out.print("👤 Enter username: ");
        String username = scanner.next();
        scanner.nextLine();
        System.out.print("🔑 Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("✅ Login successful! Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("❌ Invalid credentials. Try again.");
            return false;
        }
    }

    private static void viewHistory() {
        if (history.isEmpty()) {
            System.out.println("📭 No history available yet.");
        } else {
            System.out.println("\n📜 ===== Student History =====");
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}
