package butler.io;

import butler.task.Task;
import butler.task.TaskList;

/**
 * Represents a user interface that interacts with the user.
 * <code>Ui</code> returns string responses for the user.
 */
public class Ui {
    /**
     * Constructs a user interface.
     */
    public Ui() {
    }

    /**
     * Returns a string of the list of tasks in the given <code>taskList</code>.
     *
     * @param taskList List of tasks to be listed out.
     * @return A string of the list of tasks.
     */
    public String showTaskList(TaskList taskList) {
        // Message header
        String message = "Here are your list of tasks.\n"
                + "You have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task")
                + " in total.\n";
        message += showTaskListBodyMessage(taskList);
        return message;
    }

    /**
     * Returns a response message that a task was added.
     * Task that was added is specified by <code>task</code>.
     *
     * @param task Task that was added.
     * @return Response message that a task was added.
     */
    public String showTaskIsAdded(Task task) {
        String message;
        message = "The following task has been added to task list.\n" + task;
        return message;
    }

    /**
     * Returns a response message that a task was marked as completed.
     * The task is specified by the <code>taskIndex</code> given.
     *
     * @param taskIndex Index of task marked as completed.
     * @return Response message that a task was marked as completed.
     */
    public String showTaskIsCompleted(int taskIndex) {
        String message;
        message = "Task " + taskIndex + " has been marked as complete.";
        return message;
    }

    /**
     * Returns a response message that a task was deleted.
     * Deleted task is specified by <code>taskIndex</code>.
     *
     * @param taskIndex Index of task that was deleted.
     * @return Response message that a task was deleted.
     */
    public String showTaskIsDeleted(int taskIndex) {
        String message;
        message = "Task " + taskIndex + " has been deleted.";
        return message;
    }

    /**
     * Returns a string of the given filtered list of tasks.
     *
     * @param taskList List of tasks to be listed out.
     * @return A string of the list of filtered tasks.
     */
    public String showFilteredTaskList(TaskList taskList) {
        // Message header
        String message = "Here are the matching tasks in your list.\n"
                + "You have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task")
                + " that match.\n";
        message += showTaskListBodyMessage(taskList);
        return message;
    }

    /**
     * Formats and returns the given <code>errorMessage</code>.
     *
     * @param errorMessage Error message.
     * @return Formatted error message.
     */
    public String showErrorMessage(String errorMessage) {
        return "ERRORS: " + errorMessage;
    }

    /**
     * Returns the farewell message.
     *
     * @return Farewell message.
     */
    public String showFarewellMessage() {
        String farewell;
        farewell = "It is my honor to have served you.\n"
                + "Please come back again.";
        return farewell;
    }

    /**
     * Returns a string of the given old list of tasks.
     *
     * @param taskList Old list of tasks.
     * @return String of old list of tasks.
     */
    public String showOldTaskList(TaskList taskList) {
        // Message header
        String message = "Here are the current tasks after undoing the previous commands.\n"
                + "You have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task")
                + " in total.\n";
        message += showTaskListBodyMessage(taskList);
        return message;
    }

    /**
     * Returns the message body for the list of tasks.
     *
     * @param taskList List of tasks to be listed out.
     * @return Message body for the list of tasks.
     */
    private String showTaskListBodyMessage(TaskList taskList) {
        String taskListBodyMessage = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            taskListBodyMessage += "\n" + (i + 1) + ": " + taskList.getTask(i);
        }
        return taskListBodyMessage;
    }
}
