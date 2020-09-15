package duke;

import java.util.List;

/**
 * Control all jobs related to the User Interface
 * the class has methods to greet users & print feedback for users' input
 */
public class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static StringBuilder response;

    /**
     * print greeting content
     */

    private static void println(Object message) {
        response.append(message.toString());
        response.append("\n");
    }

    public static void greet() {
        println("Hello from\n" + logo);
        println("Hello! I'm Duke");
    }

    /**
     * print the feedback after adding a task
     */
    public static void addTask(List<Task> lst) {
        println("Got it. I've added this task: ");
        println(lst.get(lst.size() - 1));
        println(String.format("Now you have %d tasks in the list.%n", lst.size()));
    }

    /**
     * print the feedback after marking a task as done
     */
    public static void markDone(Task task) {
        println("Nice! I've marked this task as done:");
        println(task);
    }

    /**
     * print the feedback after deleting a task
     */
    public static void delete(Task task, List<Task> lst) {
        println("Noted. I've removed this task:");
        println(task);
        println(String.format("Now you have %d tasks in the list.%n", lst.size()));
    }

    /**
     * print the list of all tasks
     */
    public static void list(List<Task> lst) {
        println("Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            println((i + 1) + "." + lst.get(i));
        }
    }

    /**
     * print the exit message
     */
    public static void exit() {
        println("Bye. Hope to see you again soon!");
    }

    /**
     * initiate the class
     */
    public static void init() {
        response = new StringBuilder();
    }

    /**
     * print the error when users' input are invalid
     *
     * @param e the exception to be printed
     */
    public static void printException(IllegalArgumentException e) {
        println(e.getMessage());
    }

    /**
     * print the find result
     *
     * @param foundTasks list of tasks matches the find pattern
     */
    public static void find(List<Task> foundTasks) {
        println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            println((i + 1) + "." + foundTasks.get(i));
        }
    }

    public static String getResponse() {
        String responseToReturn = response.toString();
        response = new StringBuilder();
        return responseToReturn;
    }
}
