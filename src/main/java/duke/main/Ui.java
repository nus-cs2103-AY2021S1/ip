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
    public String getGreetingMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String message = "Quackkk! Duke reporting for duty\nWhat can I do for you master?";
        return logo + message;
    }

    /**
     * Gets farewell message to the users when they exits Duke program.
     */
    public String getGoodByeMessage() {
        return "Bye Master. Hope to see you again soon! QUACK QUACK!!!";
    }

    /**
     * Adds indentation to the message.
     *
     * @param message The message to be indented.
     * @return The indented message.
     */
    private String indentMessage(String message) {
        return INDENTATION + message;
    }

    /**
     * Adds line break to the end of the message.
     *
     * @param message The message string to be added a line break.
     * @return The message with a line break at the end of the string.
     */
    private String addLineBreak(String message) {
        return message + "\n";
    }

    /**
     * Builds a complete message from multiple small messages.
     *
     * @param strings String array contains all the small messages.
     * @return The final complete message.
     */
    private String buildMessage(String[] strings) {
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            String message = strings[i];
            result += addLineBreak(message);
        }
        return result;
    }

    /**
     * Returns the response to users when they mark a task as done.
     *
     * @param task The task that is marked as done.
     * @return The message notifying user that the task is marked as done.
     */
    public String getMarkTaskAsDoneMessage(Task task) {
        String result = "QUACKK! I've marked this task as done:\n";
        result += indentMessage(task.toString());
        result = addLineBreak(result);
        return result;
    }

    /**
     * Returns the response to users when they modify the task list.
     *
     * @param message Message to be included.
     * @param task    The task that is being added/deleted from the task list.
     * @param tasks   The task list of the user.
     * @return The message notifying the user of the current task list.
     */
    private String taskListModify(String message, Task task, TaskList tasks) {
        assert task != null : "task does not exist";
        assert tasks != null : "task list does not exist";
        String newTaskListLength = "Master, you now have " + tasks.getSize() + " items in the list! Quack! Quack!";
        String taskDescription = indentMessage(task.toString());
        String[] strings = {message, taskDescription, newTaskListLength};
        String finalMessage = buildMessage(strings);
        return finalMessage;
    }

    /**
     * Returns the messages to the user when they add a task to the task list.
     *
     * @param task  The task to be added.
     * @param tasks The user's task list.
     * @return The message notifying the user that the task is successfully added
     *         to the task list.
     */
    public String getAddTaskMessage(Task task, TaskList tasks) {
        return taskListModify("Quack Quack Quack. I've added this task: ", task, tasks);
    }

    /**
     * Returns the messages to the user when they delete a task from the task list.
     *
     * @param task  The task to be deleted.
     * @param tasks The user's task list.
     * @return The message notifying the user that a task is successfully deleted
     *         from the task list.
     */
    public String getDeleteTaskMessage(Task task, TaskList tasks) {
        return taskListModify("Quackkk. I've removed this task for you: ", task, tasks);
    }

    /**
     * Returns the message which is the task list containing all the tasks having a certain keyword.
     *
     * @param tasks Task list that contains all the tasks having a certain keyword.
     * @return The message notifying the user of the tasks that contain the particular keyword.
     */
    public String getMatchingList(TaskList tasks) {
        String messageWhenMatch = "Quack Quack! Here are the matching tasks in your list:\n";
        String messageNoMatch = "Quack :'( There's no matching task in the list:\n";
        int numOfTasks = tasks.getSize();
        boolean hasMatch = numOfTasks != 0;
        for (int i = 0; i < numOfTasks; i++) {
            int taskNumber = i + 1;
            String taskDetail = tasks.getTask(i).toString();
            String taskDetailWithLineBreak = addLineBreak(taskDetail);
            messageWhenMatch = messageWhenMatch + indentMessage(taskNumber + "." + taskDetailWithLineBreak);
        }
        return hasMatch ? messageWhenMatch : messageNoMatch;
    }

    /**
     * Returns the message containing the full task list to the user.
     *
     * @param tasks The user's task list.
     * @return The message notifying user of the current task list.
     */
    public String getFullList(String header, TaskList tasks) {
        String message = header;
        int numOfTasks = tasks.getSize();
        for (int i = 0; i < numOfTasks; i++) {
            int taskNumber = i + 1;
            String taskDetail = tasks.getTask(i).toString();
            String taskDetailWithLineBreak = addLineBreak(taskDetail);
            message = message + indentMessage(taskNumber + "." + taskDetailWithLineBreak);
        }
        if (numOfTasks == 0) {
            return "Your task list is empty";
        } else {
            return message;
        }
    }

    /**
     * Prints out error to the user when an error is met.
     *
     * @param error The error to be printed.
     */
    public void printError(DukeException error) {
        printToScreen(error.toString());
    }
}
