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
     * This method gets the next line from the scanner.
     * @return This returns the next line from the scanner.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * This method prints a greeting when the user starts the program.
     */
    public void printGreeting() {
        System.out.println("Yooo, I'm Duke.\nWhat can I do for you today?\n"
              + "Please enter dates and times like this: yyyy-mm-dd hhmm");
    }

    /**
     * This method prints a goodbye message when the user closes the program.
     */
    public void printGoodbye() {
        System.out.println("Bye bye!!! See you again next time :)");
    }

    /**
     * This method prints the error message of an exception.
     * @param e The exception which we want to print its error message.
     */
    public void printLoadingError(final Exception e) {
        System.out.println(e);
    }

    /**
     * This method prints all the tasks in a task list.
     * @param list The task list to be printed.
     * @return Returns string containing what was printed.
     */
    public String printTasks(final TaskList list) {
        String result = "Here are the tasks in your list:\n";
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).display());
            result += i + 1 + ". " + list.get(i).display() + "\n";
        }
        return result;
    }

    /**
     * Prints out all tasks in the list.
     * @param list  Task list containing tasks to be printed.
     * @return String of message to be printed.
     */
    public String printMatchingTasks(final TaskList list) {
        String result = "Here are the matching tasks in your list:\n";
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).display());
            result += i + 1 + ". " + list.get(i).display() + "\n";
        }
        return result;
    }

    /**
     * This method prints a message confirming that a task has been added.
     * @param task  The task that has been added.
     * @param list  The list to which the task has been added.
     * @return      String to be printed.
     */
    public static String printTaskAdded(final Task task,
                                      final ArrayList<Task> list) {
        System.out.println("Added task: " + task.display()
                + "\nYou have " + list.size() + " task(s) left in your list.");
        return "Added task: " + task.display()
                + "\nYou have " + list.size() + " task(s) left in your list.";
    }

    /**
     * This method prints a message confirming that a task has been done.
     * @param newTask The task that has been marked as done.
     * @return String of message to be printed.
     */
    public static String printMarkAsDone(final Task newTask) {
        System.out.println("Marked task as done:\n" + newTask.display());
        return "Marked task as done:\n" + newTask.display();
    }

    /**
     * This method prints a message confirming that a task has been deleted.
     * @param removed   The task that has been removed.
     * @param taskList  The list from which the task has been removed.
     * @return          String of message to be printed.
     */
    public static String printRemoveTask(final Task removed,
                                       final ArrayList<Task> taskList) {
        System.out.println("Removed task:\n" + removed.display()
                + "\nYou have " + taskList.size()
                + " task(s) left in your list.");
        return "Removed task:\n" + removed.display()
                + "\nYou have " + taskList.size()
                + " task(s) left in your list.";
    }
}
