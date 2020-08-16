import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        acceptCommand(sc);
    }

    private static void greet() {
        String greeting = "Hello! I'm Duke \nWhat can I do for you?";
        printResponse(greeting);
    }

    private static void acceptCommand(Scanner sc) {
        String command = sc.nextLine();

        while (true) {
            if (command.equals("bye")) {
                handleBye();
                break;
            } else if (command.equals("list")) {
                handleList();
            } else if (command.startsWith("todo")) {
                handleToDo(command);
            } else if (command.startsWith("deadline")) {
                handleDeadline(command);
            } else if (command.startsWith("event")) {
                handleEvent(command);
            } else if (command.matches("done \\d+")) {
                handleDone(command);
            } else {
                handleUnknown();
            }
            command = sc.nextLine();
        }
    }

    private static void handleBye() {
        printResponse("Bye. Hope to see you again soon!");
    }

    private static void handleList() {
        printResponse(listTasks());
    }

    private static void handleToDo(String command) {
        String[] split = command.split(" ");
        if (split.length == 1) {
            printResponse("OOPS!!! The description of a todo cannot be empty.");
        } else {
            String[] descriptionArray = Arrays.copyOfRange(split, 1, split.length);
            String description = String.join(" ", descriptionArray);
            Task task = new ToDo(description);
            tasks.add(task);
            String response = String.format(
                    "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                    task, tasks.size()
            );
            printResponse(response);
        }
    }

    private static void handleDeadline(String command) {
        String[] input = command.substring(command.indexOf(' ') + 1).split(" /by ");
        String description = input[0];
        String by = input[1];
        Task task = new Deadline(description, by);
        tasks.add(task);
        String response = String.format(
                "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                task, tasks.size()
        );
        printResponse(response);
    }

    private static void handleEvent(String command) {
        String[] input = command.substring(command.indexOf(' ') + 1).split(" /at ");
        String description = input[0];
        String at = input[1];
        Task task = new Event(description, at);
        tasks.add(task);
        String response = String.format(
                "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                task, tasks.size()
        );
        printResponse(response);
    }

    private static void handleDone(String command) {
        int index = Integer.parseInt(command.split(" ")[1]);
        if (index > tasks.size()) {
            printResponse("No such task!");
        } else {
            Task task = tasks.get(index-1);
            task.completeTask();
            String response = "Nice! I've marked this task as done:\n  " + task.toString();
            printResponse(response);
        }
    }

    private static void handleUnknown() {
        printResponse("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    // Wrapper method for printing with horizontal line borders and 1 tab indent
    private static void printResponse(String string) {
        String horizontalLine = "----------------------------------------------------------------";
        String formatted = String.format("%s\n%s\n%s", horizontalLine, string, horizontalLine)
                .replaceAll("(?m)^", "\t");
        System.out.println(formatted);
    }

    // Returns a formatted String of all the tasks
    private static String listTasks() {
        StringJoiner result = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.add(String.format("%d.%s", i+1, tasks.get(i)));
        }
        return result.toString();
    }
}
