package duke;

public class Ui {

    /**
     * Returns message of Duke Exception.
     *
     * @param e DukeException
     * @return Message of exception in String
     */
    public static String printException(DukeException e) {
        String errorMessage = "Whoops! Something went wrong...\nError Message: " + e.getMessage();
        return errorMessage;
    }

    /**
     * Informs user that the task is marked as done.
     *
     * @param task Task that is marked as done.
     */
    public static String informTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Informs user that the task is deleted.
     *
     * @param task Task that was deleted.
     */
    public static String informTaskDeleted(Task task) {
        return "Alright! I've removed this task:\n" + task;
    }

    /**
     * Informs user that the tasks are deleted.
     *
     * @param tasks Tasks that were deleted.
     */
    public static String informTasksDeleted(Task[] tasks) {
        String userMessage = "Alright! I've removed the following tasks:\n";
        for (int i = 0; i < tasks.length; i++) {
            int taskNumber = i + 1;
            userMessage += String.format("%d. %s\n", taskNumber, tasks[i]);
        }
        return userMessage;
    }

    /**
     * Informs user the number of tasks left in TaskList.
     *
     * @param taskList TaskList containing tasks.
     */
    public static String informNumberOfTasksRemaining(TaskList taskList) {
        return "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Informs user the new task was added.
     *
     * @param task New task added.
     */
    public static String informNewTask(Task task) {
        return "Roger. I've added this task:\n" + task;
    }

    /**
     * Informs user of the invalid user input.
     */
    public static String informInvalidCommand() {
        return "I'm sorry, but I don't know what that means :(";
    }

    /**
     * Informs user no matching tasks was found.
     */
    public static String informNoTaskFound() {
        return "Sorry, no matching task was found!";
    }
}
