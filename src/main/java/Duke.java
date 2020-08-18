package main.java;
import java.util.*;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void startMessage() {
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
    }
    private static void endMessage() {
        System.out.println("------------------------------------------");
        System.out.println();
    }
    private static void greet() {
        startMessage();
        System.out.println("Hello from\n" + logo);
        endMessage();
    }
    private static List<String> tokenize(String cmd) {
        String[] splitted = cmd.split(" /");
        List<String> out = new ArrayList<>();
        for (String token : splitted) {
            String[] splitted2 = token.split(" ", 2);
            for (String tokenn : splitted2) out.add(tokenn);
        }
        return out;
    }
    private static void handleCommand(String cmd) {
        startMessage();
        List<String> tokens = tokenize(cmd);
        Task task;
        switch (tokens.get(0)) {
            case "bye":
                System.out.println("See you again!");
                break;
            case "list":
                System.out.println("Here are the tasks in your list: ");
                Task.printTasks();
                break;
            case "done":
                handleDone(Integer.parseInt(tokens.get(1)));
                break;
            case "delete":
                handleRemove(Integer.parseInt(tokens.get(1)));
                break;
            case "todo":
            case "deadline":
            case "event":
                handleAdd(tokens);
                break;
            default:
                System.out.println("Sorry, I don't know what that means.");
        }
        endMessage();
    }

    private static void handleDone(int index) {
        try {
            Task.iDone(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    " + Task.getTask(index));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleAdd(List<String> tokens) {
        try {
            Task task;
            try {
                switch (tokens.get(0)) {
                    case "todo":
                        task = new Todo(tokens.get(1));
                        break;
                    case "deadline":
                        task = new Deadline(tokens.get(1), tokens.get(3));
                        break;
                    case "event":
                        task = new Event(tokens.get(1), tokens.get(3));
                        break;
                    default:
                        System.out.println("An unexpected error has occurred");
                        return;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Missing argument(s)");
                return;
            }

            Task.addTask(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println("    " + task);
            System.out.printf("Now you have %d tasks in the list. \n", Task.count());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleRemove(int index) {
        try {
            Task task = Task.iRemove(index);
            System.out.println("Noted. I've removed this task: ");
            System.out.println("    " + task);
            System.out.printf("Now you have %d tasks in the list. \n", Task.count());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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

