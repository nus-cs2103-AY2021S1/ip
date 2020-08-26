package main.java.duke.main;

import main.java.duke.exception.DukeException;

import main.java.duke.task.Task;
import main.java.duke.task.TaskList;

/**
 * Deals with the interaction and prints information to the user.
 */
public class Ui {
    private static final String INDENTATION = "    ";

    private void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints greetings to users upon opening Duke.
     */
    public void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String sentence = "Hola! Duke reporting for duty\nWhat can I do for you sir?";
        print(logo + sentence);
    }

    /**
     * Prints farewell to the users when they stop using Duke.
     */
    public void goodBye() {
        print("Bye Master. Hope to see you again soon!");
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
    public void markTaskAsDone(Task task) {
        String result = "Nice! I've marked this task as done:\n";
        result += indentMessage(task.toString());
        print(result);
    }

    /**
     * Prints out response to users when they modify the task list.
     * @param message Message to be included.
     * @param task The task that is being added/deleted from the task list.
     * @param taskList The task list of the user.
     */
    private void taskListModify(String message, Task task, TaskList taskList) {
        String newTaskListLength = "Now you have " + taskList.getSize() + " items in the list";
        String taskDescription = indentMessage(task.toString());
        String[] strings = {message, taskDescription, newTaskListLength};
        String result = buildMessage(strings);
        print(result);
    }

    /**
     * Prints out messages to the user when they add a task to the task list.
     * @param task The task to be added.
     * @param taskList The user's task list.
     */
    public void uiAddTask(Task task, TaskList taskList) {
        taskListModify("Got it. I've added this task: ", task, taskList);
    }

    /**
     * Prints out messages to the user when they delete a task from the task list.
     * @param task The task to be deleted.
     * @param taskList The user's task list.
     */
    public void uiDeleteTask(Task task, TaskList taskList) {
        taskListModify("Noted. I've removed this task: ", task, taskList);
    }

    /**
     * Prints out the task list containing all the tasks having a certain keyword.
     * @param taskList Task list that contains all the tasks having a certain keyword,
     */
    public void printMatchingList(TaskList taskList) {
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            int taskNumber = i + 1;
            String taskDescription = taskNumber + "." + taskList.getTask(i).toString()
                    + (i == taskList.getSize() - 1 ? "" : "\n");
            result = result + taskNumber + "." + indentMessage(taskDescription);
        }
        print(result);
    }

    /**
     * Prints out the full task list to the user.
     * @param taskList The user's task list.
     */
    public void printFullList(TaskList taskList) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            int taskNumber = i + 1;
            String taskDescription = taskNumber + "." + taskList.getTask(i).toString()
                    + (i == taskList.getSize() - 1 ? "" : "\n");
            result = result + taskNumber + "." + indentMessage(taskDescription);
        }
        print(result);
    }

    /**
     * Prints out error to the user when an error is met.
     * @param error The error to be printed.
     */
    public void printError(DukeException error) {
        print(error.toString());
    }
}
