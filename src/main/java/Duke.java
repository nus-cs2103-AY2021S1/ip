package main.java;
import java.util.*;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void startMessage() {
        System.out.println("┌────────────────────────────────────────────────┐");
    }
    private static void endMessage() {
        System.out.println("└────────────────────────────────────────────────┘");
    }
    private static void greet() {
        startMessage();
        System.out.println("Hello from\n" + logo);
        endMessage();
    }
    private static void handleCommand(String cmd) {
        startMessage();
        switch (cmd) {
            case "bye":
                System.out.println("See you again!");
                break;
            case "list":
                handleList();
                break;
            default:
                handleDefault(cmd);
        }
        endMessage();
    }

    private static void handleList() {
        Task.printTasks();
    }

    private static void handleDefault(String cmd) {
        Task.addTask(cmd);
        System.out.println("added: " + cmd);
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            input = sc.nextLine();
            handleCommand((input));
        }
        while (!input.equals("bye"));
    }
}

class Task {
    private static List<String> database = new ArrayList<>();

    public static void addTask(String cmd) {
        database.add(cmd);
    }

    public static void printTasks() {
        for (int i = 0; i < database.size(); i++) {
            System.out.println((i + 1) + ". " + database.get(i));
        }
    }
}

