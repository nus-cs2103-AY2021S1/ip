import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;

public class Duke {
    private static final String HORIZONTAL_LINE =
            "\t-------------------------------------------------------";
    private static final String STANDARD_GREETING = HORIZONTAL_LINE
            + "\n\tHello! I'm Duke\n" + "\t" + "What can I do for you?\n"
            + HORIZONTAL_LINE;
    private static final String GOODBYE_MESSAGE = "\tBye. Hope to see you again soon!";
    private static final String ERROR_PREFIX = "\t\u2639" + " OOPS!!! ";
    private static final String CURRENT_TASKS = "\tNow you have %d task(s) in the list.";
    private static final String INVALID_TASK = ERROR_PREFIX + "Sorry, that is not a valid task.";
    private static final ArrayList<Task> list = new ArrayList<>();

    private static void updateList(String userInput) {
        Scanner inputScanner = new Scanner(userInput);
        String command = inputScanner.next();

        switch (command.toLowerCase()) {
        case "done":
            int index = inputScanner.nextInt();
            try {
                Task task = list.get(index - 1);
                task.markAsDone();
                System.out.println("\tNice! I've marked this as done:");
                System.out.println("\t  " + task.toString());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(INVALID_TASK);
            }
            break;
        case "delete":
            index = inputScanner.nextInt();
            try {
                System.out.println("\tNoted. I've removed this task:");
                System.out.println("\t" + list.remove(index - 1).toString());
                System.out.println(String.format(CURRENT_TASKS, list.size()));
            } catch (IndexOutOfBoundsException e) {
                System.out.println(INVALID_TASK);
            }
            break;
        case "todo":
            if (!inputScanner.hasNext()) {
                System.out.println(ERROR_PREFIX
                        + "The description of a todo cannot be empty.");
            } else {
                System.out.println("\tGot it. I've added this task:");
                ToDo todo = new ToDo(inputScanner.nextLine());
                list.add(todo);
                System.out.println("\t  " + todo.toString());
                System.out.println(String.format(CURRENT_TASKS, list.size()));
            }
            break;
        case "deadline":
            if (!inputScanner.hasNext()) {
                System.out.println(ERROR_PREFIX
                        + "The description of a deadline cannot be empty.");
            } else {
                System.out.println("\tGot it. I've added this task:");
                inputScanner.useDelimiter("( /by )");
                String description = inputScanner.next();
                inputScanner.reset();
                inputScanner.next();
                String by = inputScanner.nextLine().trim();
                Deadline deadline = new Deadline(description, by);
                list.add(deadline);
                System.out.println("\t  " + deadline.toString());
                System.out.println(String.format(CURRENT_TASKS, list.size()));
            }
            break;
        case "event":
            if (!inputScanner.hasNext()) {
                System.out.println(ERROR_PREFIX
                        + "The description of an event cannot be empty.");
            } else {
                System.out.println("\tGot it. I've added this task:");
                inputScanner.useDelimiter("( /at )");
                String description = inputScanner.next();
                inputScanner.reset();
                inputScanner.next();
                String at = inputScanner.nextLine().trim();
                Event event = new Event(description, at);
                list.add(event);
                System.out.println("\t  " + event.toString());
                System.out.println(String.format(CURRENT_TASKS, list.size()));
            }
            break;
        default:
            System.out.println(ERROR_PREFIX
                    + "I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

    public static void main(String[] args) {
        System.out.println(STANDARD_GREETING);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            System.out.println(HORIZONTAL_LINE);
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(GOODBYE_MESSAGE);
                System.out.println(HORIZONTAL_LINE);
                scanner.close();
                break;
            }
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("\tHere are the tasks in your list:");
                int index = 1;
                for (Task task : list) {
                    System.out.println("\t" + index++ + ". " + task.toString());
                }
                if (index == 1) {
                    System.out.println("\tYou have no tasks in your list :)");
                }
            } else {
                updateList(userInput);
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }
}

