package duke.main;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with the interaction and prints information to the user.
 */
public class Ui {
    private static final String INDENTATION = "    ";

    public void printToScreen(String message) {
        System.out.println(message);
    }

    /**
     * Prints greetings to users upon opening Duke.
     */
    public String greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String sentence = "Hola! Duke reporting for duty\nWhat can I do for you sir?";
        return logo + sentence;
    }

    /**
     * Prints farewell to the users when they stop using Duke.
     */
    public String goodBye() {
        return "Bye Master. Hope to see you again soon!";
    }

    /**
     * Adds indentation to the message.
     * @param message The message to be indented.
     * @return The indented message.
     */
    private String indentMessage(String message) {
        return INDENTATION + message;
    }

    /**
     * Builds a complete message from multiple small messages.
     * @param strings String array contains all the small messages.
     * @return The final complete message.
     */
    private String buildMessage(String[] strings) {
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            result += strings[i] + (i == (strings.length - 1) ? "" : "\n");
        }
        return result;
    }

    /**
     * Prints out response to users when they mark a task as done.
     * @param task The task that is marked as done.
     */
    public String markTaskAsDone(Task task) {
        String result = "Nice! I've marked this task as done:\n";
        result += indentMessage(task.toString());
        return result;
    }

    /**
     * Prints out response to users when they modify the task list.
     * @param message Message to be included.
     * @param task The task that is being added/deleted from the task list.
     * @param taskList The task list of the user.
     */
    private String taskListModify(String message, Task task, TaskList taskList) {
        String newTaskListLength = "Now you have " + taskList.getSize() + " items in the list";
        String taskDescription = indentMessage(task.toString());
        String[] strings = {message, taskDescription, newTaskListLength};
        String result = buildMessage(strings);
        return result;
    }

    /**
     * Prints out messages to the user when they add a task to the task list.
     * @param task The task to be added.
     * @param taskList The user's task list.
     */
    public String uiAddTask(Task task, TaskList taskList) {
        return taskListModify("Got it. I've added this task: ", task, taskList);
    }

    /**
     * Prints out messages to the user when they delete a task from the task list.
     * @param task The task to be deleted.
     * @param taskList The user's task list.
     */
    public String uiDeleteTask(Task task, TaskList taskList) {
        return taskListModify("Noted. I've removed this task: ", task, taskList);
    }

    /**
     * Prints out the task list containing all the tasks having a certain keyword.
     * @param taskList Task list that contains all the tasks having a certain keyword,
     */
    public String getMatchingList(TaskList taskList) {
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            int taskNumber = i + 1;
            String taskDescription = taskNumber + "." + taskList.getTask(i).toString()
                    + (i == taskList.getSize() - 1 ? "" : "\n");
            result = result + taskNumber + "." + indentMessage(taskDescription);
        }
        return result;
    }

    /**
     * Prints out the full task list to the user.
     * @param taskList The user's task list.
     */
    public String getFullList(TaskList taskList) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            int taskNumber = i + 1;
            String taskDescription = taskNumber + "." + taskList.getTask(i).toString()
                    + (i == taskList.getSize() - 1 ? "" : "\n");
            result = result + taskNumber + "." + indentMessage(taskDescription);
        }
        return result;
    }

    /**
     * Prints out error to the user when an error is met.
     * @param error The error to be printed.
     */
    public void printError(DukeException error) {
        printToScreen(error.toString());
    }
}
