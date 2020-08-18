import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final static List<Task> tasks = new ArrayList<>();

    public static void printGreeting() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printPrompt(LOGO
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
        );
    }

    private static String createPrompt(String promptText) {
        String HORIZONTAL_LINE = "____________________________________________________________";
        String INDENTATION = "    ";
        String[] lines = promptText.split("[\\r\\n]+");
        StringBuilder output = new StringBuilder(INDENTATION + HORIZONTAL_LINE + '\n');

        for (String line : lines) {
            output.append(INDENTATION).append(line).append('\n');
        }

        output.append(INDENTATION).append(HORIZONTAL_LINE).append('\n');

        return output.toString();
    }

    public static void printPrompt(String promptText) {
        System.out.println(createPrompt(promptText));
    }

    public static void printAddTask(Task task) {
        printPrompt("Got it. I've added this task:\n  "
                + task + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void addTodo(String description) {
        Todo todo = new Todo(description);

        tasks.add(todo);
        printAddTask(todo);
    }

    public static void addDeadline(String input) {
        int index = input.indexOf(" /by ");
        String description = index == -1 ? input : input.substring(0, index);
        String by = index == -1 ? "" : input.substring(index + 5);
        Deadline deadline = new Deadline(description, by);

        tasks.add(deadline);
        printAddTask(deadline);
    }

    public static void addEvent(String input) {
        int index = input.indexOf(" /at ");
        String description = index == -1 ? input : input.substring(0, index);
        String at = index == -1 ? "" : input.substring(index + 5);
        Event event = new Event(description, at);

        tasks.add(event);
        printAddTask(event);
    }

    public static void doTask(Task task) {
        task.markAsDone();
        printPrompt("Nice! I've marked this task as done:\n  "
                + task);
    }

    public static void listTasks() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");

        for (Task task : tasks) {
            output.append(tasks.indexOf(task) + 1).append(".").append(task).append('\n');
        }

        printPrompt(output.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            int index = input.indexOf(" ");
            String command = index == -1 ? input : input.substring(0, index);
            String otherInput = input.substring(index + 1);

            switch (command) {
                case "list":
                    listTasks();

                    break;
                case "done":
                    int taskNo = Integer.parseInt(otherInput);

                    if (taskNo > 0 && taskNo <= tasks.size()) {
                        doTask(tasks.get(taskNo - 1));
                    } else {
                        printPrompt("Task " + taskNo + " does not exist.");
                    }

                    break;
                case "todo":
                    addTodo(otherInput);

                    break;
                case "deadline":
                    addDeadline(otherInput);

                    break;
                case "event":
                    addEvent(otherInput);

                    break;
                default:
                    break;
            }

            input = scanner.nextLine();
        }

        printPrompt("Bye. Hope to see you again soon!");
    }
}
