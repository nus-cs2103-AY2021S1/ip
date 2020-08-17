package main.java;
import java.util.*;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void startMessage() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
    }
    private static void endMessage() {
        System.out.println("------------------------------------------");
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
        switch (tokens.get(0)) {
            case "bye":
                System.out.println("See you again!");
                break;
            case "list":
                System.out.println("Here are the tasks in your list: ");
                Task.printTasks();
                break;
            case "done":
                try {
                    int index = Integer.parseInt(tokens.get(1));
                    Task.iDone(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    " + Task.getTask(index));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                break;
            case "remove":
                try {
                    int index = Integer.parseInt(tokens.get(1));
                    Task task = Task.iRemove(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    " + Task.getTask(index));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            default:
                Task task;
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
                        System.out.println("Something when wrong");
                        return;
                }
                Task.addTask(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println("    " + task);
                System.out.printf("Now you have %d tasks in the list. \n", Task.count());
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

