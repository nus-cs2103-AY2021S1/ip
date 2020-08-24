package main.java;
import java.time.DateTimeException;
import java.util.*;

enum Command {
    BYE,
    LIST,
    DONE,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    CLEAR;
}

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
        if (out.get(0).equals("todo") || out.get(0).equals("deadline") || out.get(0).equals("event")) {
            if (out.size() >= 3) out.remove(2);
            if (out.size() <= 2) out.add("null");
            out.add("0");
        }
        return out;
    }
    private static void handleCommand(String cmd) {
        startMessage();
        List<String> tokens = tokenize(cmd);
        Task task;
        Command command;
        try {
            command = Command.valueOf(tokens.get(0).toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry I don't understand what that means");
            return;
        }
        switch (command) {
            case BYE:
                Tasks.writeFile();
                System.out.println("See you again!");
                break;
            case LIST:
                System.out.println("Here are the tasks in your list: ");
                Tasks.printTasks();
                break;
            case DONE:
                handleDone(Integer.parseInt(tokens.get(1)));
                break;
            case DELETE:
                handleRemove(Integer.parseInt(tokens.get(1)));
                break;
            case CLEAR:
                Tasks.clearAll();
                System.out.println("All tasks cleared!");
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                handleAdd(tokens);
                break;
            default:
                System.out.println("An expected error has occurred.");
        }
        endMessage();
    }

    private static void handleDone(int index) {
        try {
            Tasks.iDone(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    " + Tasks.getTask(index));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleAdd(List<String> tokens) {
        Task task;
        try {
            task = Tasks.addTask(tokens);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Missing argument(s)");
            return;
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("An expected error has occurred.");
            return;
        }
        System.out.println("Got it. I've added this task: ");
        System.out.println("    " + task);
        System.out.printf("Now you have %d tasks in the list. \n", Tasks.count());
    }

    private static void handleRemove(int index) {
        try {
            Task task = Tasks.iRemove(index);
            System.out.println("Noted. I've removed this task: ");
            System.out.println("    " + task);
            System.out.printf("Now you have %d tasks in the list. \n", Tasks.count());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        greet();

        Tasks.readFile();
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            input = sc.nextLine();
            handleCommand(input);
        }
        while (!input.equals("bye"));
        sc.close();
    }
}











