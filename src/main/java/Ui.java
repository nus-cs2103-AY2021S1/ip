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
    protected Scanner sc;

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
        System.out.println("Yooo, I'm Duke.\nWhat can I do for you today?\nPlease enter dates and times in this format: yyyy-mm-dd hhmm"); //Greeting
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
    public void printLoadingError(Exception e) {
        System.out.println(e);
    }

    /**
     * This method prints all the tasks in a task list.
     * @param list The task list to be printed.
     */
    public void printTasks(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).display());
        }
    }

    /**
     * This method prints a message confirming that a task has been added.
     * @param task  The task that has been added.
     * @param list  The list to which the task has been added.
     */
    public static void printTaskAdded(Task task, ArrayList<Task> list) {
        System.out.println("Added task: " + task.display()
                + "\nYou have " + list.size() + " task(s) left in your list.");
    }

    /**
     * This method prints a message confirming that a task has been marked as done.
     * @param newTask The task that has been marked as done.
     */
    public static void printMarkAsDone(Task newTask) {
        System.out.println("Marked task as done:\n" + newTask.display());
    }

    /**
     * This method prints a message confirming that a task has been deleted.
     * @param removed   The task that has been removed.
     * @param taskList  The list from which the task has been removed.
     */
    public static void printRemoveTask(Task removed, ArrayList<Task> taskList) {
        System.out.println("Removed task:\n" + removed.display()
                + "\nYou have " + taskList.size() + " task(s) left in your list.");
    }
}
