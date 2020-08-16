import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "___________________________________________________________________________";

    private static final String LOGO = "             _        ______   _____    ______   _____  \n" +
            "     /\\     | |      |  ____| |  __ \\  |  ____| |  __ \\ \n" +
            "    /  \\    | |      | |__    | |__) | | |__    | |  | |\n" +
            "   / /\\ \\   | |      |  __|   |  _  /  |  __|   | |  | |\n" +
            "  / ____ \\  | |____  | |      | | \\ \\  | |____  | |__| |\n" +
            " /_/    \\_\\ |______| |_|      |_|  \\_\\ |______| |_____/ \n";

    private static void printToConsole(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    private static String convertTaskListToString(List<Task> tasks) {

        if (tasks.isEmpty()) {
            return "You have no tasks!";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i+1));
            sb.append(".");
            sb.append(tasks.get(i));
            sb.append('\n');
        }

        // remove last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private static String markTaskAsDone(List<Task> tasks, int taskID) {
        Task task = tasks.get(taskID - 1);
        task.competeTask();
        return String.format("Nice! I've marked this task as done.\n %s", task.toString());
    }

    private static String addTaskToList(List<Task> tasks, Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task: \n %s \n Now you have %d tasks in the list",
                task.toString(), tasks.size());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        System.out.println(LOGO);
        printToConsole("Hi I'm Alfred! How can I help you today?");

        while (sc.hasNextLine()) {
            String[] inputList = sc.nextLine().split(" ", 2);
            String command = inputList[0];
            String argument = inputList.length > 1 ? inputList[1] : "";

            switch (command) {
            case "bye":
                printToConsole("Goodbye!");
                return;
            case "list":
                printToConsole(convertTaskListToString(tasks));
                break;
            case "done":
                printToConsole(markTaskAsDone(tasks, Integer.parseInt(inputList[1])));
                break;
            case "todo":
                printToConsole(addTaskToList(tasks, new ToDo(argument)));
                break;
            case "event":
                String[] eventArguments = argument.split(" /at ");
                printToConsole(addTaskToList(tasks, new Event(eventArguments[0], eventArguments[1])));
                break;
            case "deadline":
                String[] deadlineArguments = argument.split(" /by ");
                printToConsole(addTaskToList(tasks, new Deadline(deadlineArguments[0], deadlineArguments[1])));
                break;
            default:
                printToConsole("unsupported command");
            }
        }

        sc.close();
    }
}