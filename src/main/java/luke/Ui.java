package luke;

import luke.exception.LukeException;
import luke.task.Task;

/**
 * Represents Ui that directly interacts with user.
 */
public class Ui {

    public Ui() {

    }

    /**
     * Show welcome message to the user.
     *
     * @return welcome message
     */
    public String showWelcome() {
        return "Hey there! I'm Luke.\n"
                + "Please tell me what to do.\n";
    }

    /**
     * Show error message to the user.
     *
     * @return error message
     */
    public String showError(LukeException e) {
        return e.getMessage();
    }

    /**
     * Show add result to the user.
     *
     * @param newTask task to be added
     * @param numOfTasks number of current tasks
     * @return result of adding task
     */
    public String showAddResult(Task newTask, int numOfTasks) {
        assert numOfTasks >= 0 : "Number of tasks should not be negative";
        return String.format("The following task has been successfully added.\n"
                + "-> %s\n"
                + "Now you have %d tasks in your list.\n", newTask, numOfTasks);
    }

    /**
     * Show delete result to the user.
     *
     * @param newTask task to be deleted
     * @param numOfTasks number of current tasks
     * @return result of deleting task
     */
    public String showDeleteResult(Task newTask, int numOfTasks) {
        assert numOfTasks >= 0 : "Number of tasks should not be negative";
        return String.format("The following task has been successfully deleted.\n"
                + "-> %s\n"
                + "Now you have %d tasks in your list.\n", newTask, numOfTasks);
    }

    /**
     * Show done result to the user.
     *
     * @param newTask task to be marked as done
     * @return result of marking task as done
     */
    public String showDoneResult(Task newTask) {
        return String.format("The following task has been successfully marked as done.\n"
                + "-> %s\n", newTask);
    }

    /**
     * Show list result to the user.
     *
     * @param tasks list of current tasks
     * @return result of listing out task
     */
    public String showListResult(TaskList tasks) {
        if (tasks.getSize() < 1) {
            return "You don't have any tasks in your list :(";
        } else {
            String taskSummary = "Here are the tasks in your list.\n";
            for (int i = 0; i < tasks.getSize(); i++) {
                Task current = tasks.getTask(i);
                taskSummary += String.format("-> %d.%s\n", i + 1, current);
            }
            return String.format("%s", taskSummary);
        }
    }

    public String showExitResult() {
        return "";
    }
}
