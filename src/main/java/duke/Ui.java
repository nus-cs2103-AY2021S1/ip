package duke;

import java.util.ArrayList;

/**
 * Represents the interface for Duke to display the chat box output.
 */
public class Ui {
    /**
     * Gets chat divider.
     * @return divider line.
     */
    public String getChatDivider() {
        return "____________________________________________________________\n";
    }

    /**
     * Gets welcome message.
     * @return welcome message from Duke.
     */
    public String getWelcomeMessage() {
        String logo = " " + "____             _        \n"
            + "|   _  \\  _    _| | ____ \n"
            + "| |  | |  |  | | |/ / _ \\\n"
            + "| |_| |  |_| |   <| __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo
            + "Hi! I'm Duke \nWhat can I do for you?\n";
    }

    /**
     * Gets goodbye message.
     * @return goodbye message from Duke.
     */
    public String getGoodbyeMessage() {
        return "Bye. See you again next time!";
    }

    /**
     * Gets message when a new task is added.
     * @param task the task added.
     * @param numberOfTask the current number of tasks.
     * @return message for added task.
     */
    public String getAddedTask(Task task, int numberOfTask) {
        return "Got it. I've added this task: \n"
            + task.toString()
            + "\nNow you have " + numberOfTask + " tasks in the list.";
    }

    /**
     * Gets the current list of tasks.
     * @param taskList the lists of task.
     * @return the String representation of taskList.
     */
    public String getTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        StringBuilder results = new StringBuilder("Here are the tasks in your list:");
        for (int index = 0; index < tasks.size(); index++) {
            results.append("\n" + (index + 1) + ": " + tasks.get(index).toString());
        }
        return results.toString();
    }

    /**
     * Gets message when a task is marked as done.
     * @param task the task being completed.
     * @return message for completed task.
     */
    public String getCompletedTask(Task task) {
        return "Nice! I've marked this task as done:\n"
            + task.toString();
    }

    /**
     * Gets message when a task is deleted from the list.
     * @param task the task deleted.
     * @param numberOfTask the current number of tasks.
     * @return message for deleted task.
     */
    public String getDeletedTask(Task task, int numberOfTask) {
        return "Noted. I've removed this task:\n"
            + task.toString()
            + "\nNow you have " + numberOfTask + " tasks in the list.";

    }

    /**
     * Gets message when all tasks is deleted from the list.
     * @return message for delete all tasks.
     */
    public String getDeleteAllTasksMessage() {
        return "Noted. I've removed all tasks in the list.\n"
            + "Now you have no task in the list.";
    }

    /**
     * Gets message when tag is attached to a task.
     * @param task the task being tagged.
     * @return message for tagged task.
     */
    public String getTagTaskMessage(Task task) {
        return "Noted. I've added the tag to your task.\n"
            + task.toString();
    }

    /**
     * Prints the tasks in the search results.
     * @param tasks the tasks in the search results.
     * @return search results.
     */
    public String getSearchResult(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "No tasks found";
        } else {
            StringBuilder results = new StringBuilder("Here are the matching tasks in your list:");
            for (int index = 0; index < tasks.size(); index++) {
                results.append("\n" + (index + 1) + ": " + tasks.get(index));
            }
            return results.toString();
        }
    }
}
