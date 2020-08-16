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
        switch (cmd.split(" ", 2)[0]) {
            case "bye":
                System.out.println("See you again!");
                break;
            case "list":
                Task.printTasks();
                break;
            case "done":
                try {
                    int index = Integer.parseInt(cmd.split(" ", 2)[1]);
                    Task.iDone(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(Task.getTask(index));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                break;
            default:
                Task.addTask(cmd);
                System.out.println("added: " + cmd);
        }
        endMessage();
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
    private static List<Task> database = new ArrayList<>();
    private String description;
    private boolean isDone = false;

    private Task(String cmd) {
        this.description = cmd;
    }

    public static void addTask(String cmd) {
        database.add(new Task(cmd));
    }

    public static String getTask(int index) {
        if (index <= 0 || index > database.size()) {
            throw new IllegalArgumentException("Invalid argument for the LIST command.");
        }
        return database.get(index - 1).toString();
    }

    private void markAsDone() {isDone = true;}

    public static void iDone(int index) {
        if (index <= 0 || index > database.size()) {
            throw new IllegalArgumentException("Invalid argument for the LIST command.");
        }
        database.get(index - 1).markAsDone();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (isDone ? "\u2713" : "\u2718"), description);
    }

    public static void printTasks() {
        for (int i = 0; i < database.size(); i++) {
            System.out.println((i + 1) + "." + database.get(i));
        }
    }
}

