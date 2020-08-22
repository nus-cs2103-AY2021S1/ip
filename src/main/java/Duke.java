import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class that takes in user input.
 */
public class Duke {

    /**
     * List used to store all tasks.
     */
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Prints Duke's welcome message.
     */
    private static void welcome() {
        printOutput("     Hello! I'm Duke\n"
                + "     What can I do for you?", true);
    }

    /**
     * Wraps input in dashes.
     */
    private static void printOutput(String input, boolean hasLastNewLine) {
        if (hasLastNewLine) {
            System.out.println("    ____________________________________________________________\n"
                    + input + "\n"
                    + "    ____________________________________________________________");

        } else {
            System.out.println("    ____________________________________________________________\n"
                    + input + "    ____________________________________________________________");
        }
    }

    /**
     * Handles input, and calls corresponding functions.
     *
     * @param input String input from user.
     * @return returns boolean of whether or not to continue.
     */
    private static boolean inputHandler(String input) {
        // TODO: do this (exit) in a more elegant way, else boolean method would violate coding standards
        Task task;
        String second;
        int index;

        String[] parts = input.split(" ", 2);
        String action = parts[0].toUpperCase();

        try {
            Command command = tryEnum(action);
            switch(command) {
            case LIST:
                listTasks();
                return true;
            case DONE:
                second = parts[1];
                index = Integer.parseInt(second);
                done(index);
                return true;
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                try {
                    handleTask(parts, command);
                } catch (DukeException e) {
                    printOutput(e.getMessage(), true);
                }
                return true;
            case DELETE:
                second = parts[1];
                index = Integer.parseInt(second);
                deleteTask(index);
                return true;
            case BYE:
                exit();
                return false;
            default:
                return true;
            }
        } catch (DukeException e) {
            printOutput(e.getMessage(), true);
            return true;
        }
    }

    private static void handleTask(String[] inputs, Command command) throws DukeException {
        try {
            String taskInfo = inputs[1];
            Task task;
            String[] taskInfoParts;
            String description;
            String date;
            //TODO: in switch/if-else statement, okay to define early if we use the same variable name?

            switch (command) {
            case TODO:
                task = handleTodo(taskInfo);
                break;
            case DEADLINE:
                taskInfoParts = taskInfo.split(" /by ", 2);
                description = taskInfoParts[0];
                date = taskInfoParts[1];
                task = handleDeadline(description, date);
                break;
            case EVENT:
                taskInfoParts = taskInfo.split(" /at ", 2);
                description = taskInfoParts[0];
                date = taskInfoParts[1];
                task = handleEvent(description, date);
                break;
            default:
                task = new Task("");
            }

            int len = taskList.size();
            printOutput("     Got it. I've added this task:\n       "
                    + task.toString() + "\n     Now you have " + len
                    + " tasks in the list.", true);

        } catch (ArrayIndexOutOfBoundsException e) {
            String error = "     OOPS!!! The description of a " + command.toString().toLowerCase() + " cannot be empty.";
            throw new DukeException(error);
        }
    }

    private static Command tryEnum(String action) throws DukeException {
        try {
            return Command.valueOf(action);
        } catch (IllegalArgumentException e) {
            throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Does all necessary actions before exiting.
     */
    private static void exit() {
        printOutput("     Bye. Hope to see you again soon!", true);
    }

    private static void deleteTask(int index) {
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        int len = taskList.size();
        printOutput("     Noted. I've removed this task:\n       "
                + task.toString() + "\n     Now you have " + len
                + " tasks in the list.", true);
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

    private static void listTasks() {
        StringBuilder output = new StringBuilder("     Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : taskList) {
            output.append("     ").append(index).append(".").append(task.toString()).append("\n");
            index++;
        }
        printOutput(output.toString(), false);
    }

    private static void done(int index) {
        Task task = taskList.get(index - 1);
        task.setDone(true);
        String output = "     Nice! I've marked this task as done: \n"
                + "       " + task.toString();
        printOutput(output, true);
    }

    /**
     * Reads user input, and does corresponding action.
     */
    public static void main(String[] args) {
        welcome();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            boolean shouldContinue = inputHandler(input);
            if (!shouldContinue) {
                break;
            }
        }
    }
}








