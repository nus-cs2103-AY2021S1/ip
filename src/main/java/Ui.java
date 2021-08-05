import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a UI class that handles interactions with the user.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class Ui {
    /**
     * Scanner object to take in user input.
     */
    private Scanner sc;

    /**
     * UI constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Gets the next line of input from the scanner.
     * 
     * @return This returns the next line from the scanner.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a greeting when the user starts the program.
     */
    public void printGreeting() {
        System.out.println("Heyy, I'm Grizz.\n" +
                "What can I do for you today?\n");
    }

    /**
     * Prints a goodbye message when the user closes the program.
     */
    public void printGoodbye() {
        System.out.println("Bye bye!!! See you again next time :)");
    }

    /**
     * Prints the error message of an exception.
     * 
     * @param e The exception which we want to print its error message.
     */
    public void printLoadingError(final Exception e) {
        System.out.println(e);
    }

    /**
     * Prints all the tasks in a task list.
     * 
     * @param taskList  The task list to be printed.
     * @return          The message to be printed.
     */
    public String printTasks(final TaskList taskList) {
        String result = "Here are the tasks in your list:\n";
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i).display());
            result += i + 1 + ". " + taskList.get(i).display() + "\n";
        }
        return result;
    }

    /**
     * Prints all the tasks in a task list that matches query.
     * 
     * @param taskList  Task list containing tasks to be printed.
     * @return          The message to be printed.
     */
    public String printMatchingTasks(final TaskList taskList) {
        String result = "Here are the matching tasks in your list:\n";
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(taskList.get(i).display());
            result += taskList.get(i).display() + "\n";
        }
        return result;
    }

    /**
     * Prints a message confirming that a task has been added.
     *
     * @param task  The task that has been added.
     * @param list  The list to which the task has been added.
     * @return      The message to be printed.
     */
    public static String printTaskAdded(final Task task,
                                      final ArrayList<Task> list) {
        String taskAddedMessage = "Added task: " + task.display()
                + "\nYou have " + list.size() + " task(s) left in your list.";
        System.out.println(taskAddedMessage);
        return taskAddedMessage;
    }

    /**
     * Prints a message confirming that a task has been done.
     *
     * @param task  The task that has been marked as done.
     * @return      The message to be printed.
     */
    public static String printMarkAsDone(final Task task) {
        String taskDoneMessage = "Marked task as done:\n" + task.display();
        System.out.println(taskDoneMessage);
        return taskDoneMessage;
    }

    /**
     * Prints a message confirming that a task has been deleted.
     *
     * @param task      The task that has been removed.
     * @param list      The list from which the task has been removed.
     * @return          The message to be printed.
     */
    public static String printRemoveTask(final Task task,
                                       final ArrayList<Task> list) {
        String taskRemovedMessage = "Removed task:\n" + task.display()
                + "\nYou have " + list.size()
                + " task(s) left in your list.";
        System.out.println(taskRemovedMessage);
        return taskRemovedMessage;
    }
}
