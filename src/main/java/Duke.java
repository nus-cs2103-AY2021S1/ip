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
            try {
                if (command.equals("bye")) {
                    handleBye();
                    break;
                } else if (command.equals("commands")) {
                    handleCommands();
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
                } else if (command.startsWith("delete")) {
                    handleDelete(command);
                } else {
                    handleUnknown();
                }
            } catch (DukeException e) {
                printResponse(e.getMessage());
            }
            command = sc.nextLine();
        }
    }

    private static void handleDelete(String command) throws InvalidDeleteIndexException {
        int index = Integer.parseInt(command.split(" ")[1]);
        if (index > tasks.size() || index < 1) {
            String response = String.format("No such task :(\nYou have %d tasks.", tasks.size());
            throw new InvalidDeleteIndexException(response);
        }

        Task task = tasks.remove(index-1);
        String response = String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", task, tasks.size());
        printResponse(response);
    }

    private static void handleCommands() {
        String response = "Here are all the commands:\n\n"
                + "bye\n"
                + "deadline <task> /by <time>\n"
                + "delete <task index>\n"
                + "done <task index>\n"
                + "event <task> /at <time>\n"
                + "list\n"
                + "todo <task>";
        printResponse(response);
    }

    private static void handleBye() {
        printResponse("Bye. Hope to see you again soon!");
    }

    private static void handleList() {
        printResponse(listTasks());
    }

    private static void handleToDo(String command) throws InvalidToDoException {
        String[] split = command.split(" ");
        if (split.length == 1) {
            String response = "The description of a todo cannot be empty :(\n"
                    + "The command format is \"todo <task>\"";
            throw new InvalidToDoException(response);
        }

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

    private static void handleDeadline(String command) throws InvalidDeadlineException {
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            String response = "You entered the deadline command incorrectly :(\n"
                    + "The command format is \"deadline <task> /by <time>\"";
            throw new InvalidDeadlineException(response);
        }
    }

    private static void handleEvent(String command) throws InvalidEventException {
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            String response = "You entered the event command incorrectly :(\n"
                    + "The command format is \"event <task> /at <time>\"";
            throw new InvalidEventException(response);
        }
    }

    private static void handleDone(String command) throws InvalidDoneIndexException {
        int index = Integer.parseInt(command.split(" ")[1]);
        if (index > tasks.size() || index < 1) {
            String response = String.format("No such task :(\nYou have %d tasks.", tasks.size());
            throw new InvalidDoneIndexException(response);
        }

        Task task = tasks.get(index-1);
        task.completeTask();
        String response = "Nice! I've marked this task as done:\n  " + task.toString();
        printResponse(response);
    }

    private static void handleUnknown() throws DukeException{
        String response = "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "To see all commands, type \"commands\"";
        throw new DukeException(response);
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
