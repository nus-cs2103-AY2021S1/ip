package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Handles the output to the user
 */
public class Ui {
    private String outputMessage;

    /**
     * Initializes the Ui object.
     */
    public Ui() {
        this.outputMessage = "";
    }

    /**
     * Clear the current output message
     */
    public void clearOutputMessage() {
        this.outputMessage = "";
    }

    /**
     * Get the current output message
     */
    public String getOutputMessage() {
        return this.outputMessage;
    }

    private void outputLine(String message) {
        this.outputMessage += message;
        this.outputMessage += "\n";
    }

    /**
     * Shows welcome message to user.
     */
    public void showWelcome() {
        String name = "Duke";
        this.outputLine("Hi! I am " + name + ", your personal assistant.");
        this.outputLine("How may I help you today?");
    }

    /**
     * Shows goodbye message to user.
     */
    public void showGoodbye() {
        this.outputLine("Goodbye! Shutting down now...");
    }

    /**
     * Shows loading data from disk error to user.
     */
    public void showLoadingError() {
        this.outputLine("Sorry, there is an error loading the data");
    }

    /**
     * Shows user the errorMessage passed to method.
     * @param errorMessage Error message to be shown to the user.
     */
    public void showError(String errorMessage) {
        this.outputLine(errorMessage);
    }

    /**
     * Shows all the tasks in the task list to the user.
     * @param taskList The list of tasks to be shown.
     */
    public void showAllTasks(List<Task> taskList) {
        this.outputLine("Here are the tasks in your list:");
        showTasks(taskList);
    }

    /**
     * Shows all the deadlines and events for a particular date.
     * @param taskList The list of tasks to retrieve the deadlines and events.
     * @param date The date used to filter the deadlines and events.
     */
    public void showTasksOnDate(List<Task> taskList, LocalDate date) {
        this.outputLine(String.format(
                "Here are the tasks in your list on %s:",
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
        showTasks(taskList);
    }

    /**
     * Shows a task list that has been filtered with a keyword.
     * @param taskList The filtered task list.
     */
    public void showTasksFound(List<Task> taskList) {
        if (taskList.size() > 0) {
            this.outputLine("Here are the matching tasks in your list:");
            showTasks(taskList);
        } else {
            this.outputLine("There are no matching tasks in your list :(");
        }
    }

    private void showTasks(List<Task> taskList) {
        int idx = 1;
        for (Task task : taskList) {
            this.outputLine(String.format("%d.%s", idx, task.toString()));
            idx += 1;
        }
    }

    /**
     * Shows the user the task that has been marked done.
     * @param task The task that has been marked as done.
     */
    public void showTaskDone(Task task) {
        this.outputLine("Nice! I've marked this task as done:");
        printWithIndent(task.toString());
    }

    /**
     * Shows the user the task that has been deleted, as well as the total number of tasks left.
     * @param task The task that has been deleted.
     * @param numTasksLeft The total number of tasks left.
     */
    public void showTaskDeleted(Task task, int numTasksLeft) {
        this.outputLine("Noted. I've removed this task:");
        printWithIndent(task.toString());
        this.outputLine(String.format("Now you have %d tasks in the list.", numTasksLeft));
    }

    /**
     * Shows the user the task that has been added, as well as the total number of tasks left in the list.
     * @param task The task that has been added.
     * @param numTasksLeft The total number of tasks left.
     */
    public void showTaskAdded(Task task, int numTasksLeft) {
        this.outputLine("Got it. I've added this task:");
        printWithIndent(task.toString());
        this.outputLine(String.format("Now you have %d tasks in the list.", numTasksLeft));
    }

    /**
     * Shows the user the task that has been tagged.
     * @param task The task that has been tagged.
     */
    public void showTaskTagged(Task task) {
        this.outputLine("Got it. I've tagged this task:");
        printWithIndent(task.toString());
    }

    private void printWithIndent(String str) {
        this.outputLine("  " + str);
    }
}
