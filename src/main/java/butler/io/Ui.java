package butler.io;

import butler.task.Task;
import butler.task.TaskList;

/**
 * Represents a user interface that interacts with the user.
 * <code>Ui</code> returns string statements for the user.
 */
public class Ui {
    /**
     * Constructs a user interface.
     */
    public Ui() {
    }

    /**
     * Returns a welcome message.
     *
     * @return Welcome message.
     */
    public String showWelcome() {
        String greetings = "Welcome! I am your personal manager, Butler.\n"
                + "How may I help you today?\n";
        return greetings;
    }

    /**
     * Returns a dotted line separator.
     *
     * @return Dotted line separator.
     */
    public String showLine() {
        return "\n------------------------------\n";
    }

    /**
     * Returns the formatted <code>errorMessage</code> given.
     *
     * @param errorMessage Error message to be printed.
     * @return Formatted error message.
     */
    public String showError(String errorMessage) {
        return "ERRORS: " + errorMessage;
    }

    /**
     * Returns the farewell message.
     *
     * @return Farewell message.
     */
    public String showExit() {
        String farewells = "It is my honor to have served you.\n"
                + "Please come back again.";
        return farewells;
    }

    /**
     * Returns the loading error message.
     *
     * @return Loading error message.
     */
    public String showLoadingError() {
        String loadingErrorMessage = "tasks.txt file was not detected.\n"
                + "A new task list will be created.";
        return loadingErrorMessage;
    }

    /**
     * Returns a response message that a task is marked as completed.
     * The task is specified by the <code>taskIndex</code> given.
     *
     * @param taskIndex Index of task marked as completed.
     * @return Response message that a task is marked as completed.
     */
    public String showTaskCompleted(int taskIndex) {
        String msg = "Task " + taskIndex + " has been marked as complete.";
        return msg;
    }

    /**
     * Returns a response message that a task is added.
     * Task that was added is specified by <code>task</code>.
     *
     * @param task Task that was added.
     * @return Response message that a task is added.
     */
    public String showTaskAdded(Task task) {
        String msg = "The following task has been added to task list.\n" + task;
        return msg;
    }

    /**
     * Returns a string of the list of tasks in the given <code>taskList</code>.
     *
     * @param taskList List of tasks to be printed out.
     * @return A string of the list of tasks.
     */
    public String printTaskList(TaskList taskList) {
        String msg = "Here are your list of tasks.\n"
                + "You have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task")
                + " in total.\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            msg += "\n" + (i + 1) + ": " + taskList.getTask(i);
        }
        return msg;
    }

    /**
     * Returns a response message that a task was deleted.
     * Deleted task is specified by <code>taskIndex</code>.
     *
     * @param taskIndex Index of task that was deleted.
     * @return Response message that a task was deleted.
     */
    public String showTaskDeleted(int taskIndex) {
        String msg = "Task " + taskIndex + " has been deleted.";
        return msg;
    }

    /**
     * Returns a string of the given filtered list of tasks.
     *
     * @param taskList List of tasks to be printed out.
     * @return A string of the list of filtered tasks.
     */
    public String printFilteredTaskList(TaskList taskList) {
        String msg = "Here are the matching tasks in your list.\n"
                + "You have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task")
                + " in total.\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            msg += "\n" + (i + 1) + ": " + taskList.getTask(i);
        }
        return msg;
    }
}
