package duke;

import java.util.List;

/**
 * Control all jobs related to the User Interface
 * the class has methods to greet users & print feedback for users' input
 */
public class Ui {
    protected static String separatedLine;

    /**
     * print greeting content
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(separatedLine);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you ?");
        System.out.println(separatedLine);
    }

    /**
     * print the feedback after adding a task
     */
    public static void addTask(List<Task> lst) {
        System.out.println(separatedLine);
        System.out.println("Got it. I've added this task: ");
        System.out.println(lst.get(lst.size() - 1));
        System.out.printf("Now you have %d tasks in the list.%n", lst.size());
        System.out.println(separatedLine);
    }

    /**
     * print the feedback after marking a task as done
     */
    public static void markDone(Task task) {
        System.out.println(separatedLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(separatedLine);
    }

    /**
     * print the feedback after deleting a task
     */
    public static void delete(Task task, List<Task> lst) {
        System.out.println(separatedLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.%n", lst.size());
        System.out.println(separatedLine);
    }

    /**
     * print the list of all tasks
     */
    public static void list(List<Task> lst) {
        System.out.println(separatedLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + "." + lst.get(i));
        }
        System.out.println(separatedLine);
    }

    /**
     * print the exit message
     */
    public static void exit() {
        System.out.println(separatedLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separatedLine);
    }

    /**
     * initiate the class
     */
    public static void init() {
        separatedLine = "-".repeat(30);
    }

    /**
     * print the error when users' input are invalid
     *
     * @param e the exception to be printed
     */
    public static void printException(IllegalArgumentException e) {
        System.out.println(separatedLine);
        System.out.println(e.getMessage());
        System.out.println(separatedLine);
    }

    /**
     * print the find result
     *
     * @param foundTasks list of tasks matches the find pattern
     */
    public static void find(List<Task> foundTasks) {
        System.out.println(separatedLine);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println((i + 1) + "." + foundTasks.get(i));
        }
        System.out.println(separatedLine);
    }
}
