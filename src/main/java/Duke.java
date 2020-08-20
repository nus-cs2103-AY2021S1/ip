import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

    /*
     * Prints Duke's welcome message
     */
    private static void welcome() {
        printOutput("     Hello! I'm Duke\n" +
                        "     What can I do for you?", true);
    }

    /*
     * Wraps input in dashes
     */
    private static void printOutput(String input, boolean lastNewLine) {
        if (lastNewLine) {
            System.out.println("    ____________________________________________________________\n" +
                    input + "\n" +
                    "    ____________________________________________________________");

        } else {
            System.out.println("    ____________________________________________________________\n" +
                    input +
                    "    ____________________________________________________________");
        }
    }

    /*
     * Handles input, and calls corresponding functions
     * @return: returns boolean of whether or not to continue
     */
    private static boolean inputHandler(String input) {
        String[] parts = input.split(" ", 2);
        String action = parts[0];
        Task task;
        String second;
        switch(action) {
            case "list":
                listTasks();
                return true;
            case "done":
                done(input);
                return true;
            case "todo":
                try {
                    String todoTask = parts[1];
                    task = handleTodo(todoTask);
                    handleTask(task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printOutput("     OOPS!!! The description of a todo cannot be empty.", true);
                }
                return true;
            case "deadline":
                second = parts[1];
                String[] secondDeadlineParts = second.split(" /by ", 2);
                String deadlineTask = secondDeadlineParts[0];
                String deadlineBy = secondDeadlineParts[1];
                task = handleDeadline(deadlineTask, deadlineBy);
                handleTask(task);
                return true;
            case "event":
                second = parts[1];
                String[] secondEventParts = second.split(" /at ", 2);
                String eventTask = secondEventParts[0];
                String eventAt = secondEventParts[1];
                task = handleEvent(eventTask, eventAt);
                handleTask(task);
                return true;
            case "bye":
                exit();
                return false;
            default:
                printOutput("     OOPS!!! I'm sorry, but I don't know what that means :-(", true);
                return true;
        }
    }

    /*
     * Does all necessary actions before exiting
     */
    private static void exit() {
        printOutput("     Bye. Hope to see you again soon!", true);
    }

    private static Task handleTodo(String todoTask) {
        Task todo = new Todo(todoTask);
        taskList.add(todo);
        return todo;
    }

    private static Task handleDeadline(String deadlineTask, String deadlineBy) {
        Task deadline = new Deadline(deadlineTask, deadlineBy);
        taskList.add(deadline);
        return deadline;
    }

    private static Task handleEvent(String eventTask, String eventAt) {
        Task event = new Event(eventTask, eventAt);
        taskList.add(event);
        return event;
    }

    private static void handleTask(Task task) {
        int len = taskList.size();
        printOutput("     Got it. I've added this task:\n       " +
                        task.toString() + "\n     Now you have " + len +
                        " tasks in the list.", true);
    }

    private static void listTasks() {
        String output = "     Here are the tasks in your list:\n";
        int index = 1;
        for (Task task : taskList) {
            output += "     " + index + "." + task.toString() + "\n";
            index++;
        }
        printOutput(output, false);
    }

    private static void done(String input) {
        String strIndex = input.split(" ")[1];
        int index = Integer.parseInt(strIndex);
        Task task = taskList.get(index - 1);
        task.setDone(true);
        String output = "     Nice! I've marked this task as done: \n" +
                                "       " + task.toString();
        printOutput(output, true);
    }

    /*
     * reads user input, and does corresponding action
     */
    public static void main(String[] args) {
        welcome();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (true) {
            boolean toContinue = inputHandler(input);
            if (toContinue) {
                input = scanner.nextLine();
            } else {
                break;
            }
        }
    }
}








