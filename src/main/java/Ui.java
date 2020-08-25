import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

/**
 * Handles all interaction with the user, including the reading of user input and outputting text.
 */
public class Ui {
    Scanner scanner;

    /**
     * Initializes the Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input and return it.
     * @return User's input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows welcome message to user.
     */
    public void showWelcome() {
        String name = "Omega";
        printHorizontalLine();
        System.out.println("Hi! I am " + name + ", your personal assistant.");
        System.out.println("How may I help you today?");
        printHorizontalLine();
    }

    /**
     * Shows goodbye message to user.
     */
    public void showGoodbye() {
        System.out.println("Goodbye! Shutting down now...");
    }

    /**
     * Shows loading data from disk error to user.
     */
    public void showLoadingError() {
        printHorizontalLine();
        System.out.println("Sorry, there is an error loading the data");
        printHorizontalLine();
    }

    /**
     * Shows user the errorMessage passed to method.
     * @param errorMessage Error message to be shown to the user.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Shows all the tasks in the task list to the user.
     * @param taskList The list of tasks to be shown.
     */
    public void showAllTasks(List<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        showTasks(taskList);
    }

    /**
     * Shows all the deadlines and events for a particular date.
     * @param taskList The list of tasks to retrieve the deadlines and events.
     * @param date The date used to filter the deadlines and events.
     */
    public void showTasksOnDate(List<Task> taskList, LocalDate date) {
        System.out.println(String.format(
                "Here are the tasks in your list on %s:",
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
        showTasks(taskList);
    }

    private void showTasks(List<Task> taskList) {
        int idx = 1;
        for (Task task : taskList) {
            System.out.println(String.format("%d.%s", idx, task.toString()));
            idx += 1;
        }
    }

    /**
     * Shows the user the task that has been marked done.
     * @param task The task that has been marked as done.
     */
    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        printWithIndent(task.toString());
    }

    /**
     * Shows the user the task that has been deleted, as well as the total number of tasks left.
     * @param task The task that has been deleted.
     * @param numTasksLeft The total number of tasks left.
     */
    public void showTaskDeleted(Task task, int numTasksLeft) {
        System.out.println("Noted. I've removed this task:");
        printWithIndent(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", numTasksLeft));
    }

    /**
     * Shows the user the task that has been added, as well as the total number of tasks left in the list.
     * @param task The task that has been added.
     * @param numTasksLeft The total number of tasks left.
     */
    public void showTaskAdded(Task task, int numTasksLeft) {
        System.out.println("Got it. I've added this task:");
        printWithIndent(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", numTasksLeft));
    }

    /**
     * Shows a blank line to the user.
     */
    public void showBlankLine() {
        System.out.println();
    }

    /**
     * Shows a horizontal line to the user.
     */
    public void showLine() {
        this.printHorizontalLine();
    }

    private void printHorizontalLine() {
        String horizontalLine = "---------------------------------------------";
        System.out.println(horizontalLine);
    }

    private void printWithIndent(String str) {
        System.out.println("  " + str);
    }
}
