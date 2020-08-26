package duke;
import java.util.List;
import java.util.Scanner;

/**
 * Ui class prints various response by Duke object, handles user interactions.
 */

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public Ui() {
        System.out.println("Hello! I'm meimei ^_^\nI could scream at you all day!");
    }

    /**
     * duke asks for new user input
     * @return the user input
     */
    public String ask() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("File not found >w<");
    }

    public static void bye() {
        System.out.println("Bye! Meimei will miss u :(");
    }

    /**
     * A method to respond to the user the list of tasks containing the keyword.
     * @param isEmpty is true if there are no tasks found containing the keyword.
     * @param tasks that contains the searched keyword.
     */
    public static void printFoundTask(Boolean isEmpty, List<Task> tasks) {
        if (!isEmpty) {
            System.out.println("Meimei found these matching tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        } else {
            System.out.println("No matching tasks found :(");
        }
    }

    /**
     * A method that prints the task list.
     * @param tasks to be printed.
     */

    public static void printTaskList(List<Task> tasks) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * method to print a task that was successfully added
     * @param task the added one
     * @param size of the list
     */
    public static void addedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * method to print a deleted task
     * @param task that was deleted
     */
    public static void deletedTask(Task task) {
        System.out.println("Meimei will forget about this task:\n" + task);
    }
}
