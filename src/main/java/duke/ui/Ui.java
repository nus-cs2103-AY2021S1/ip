package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the user interface of the Duke chat bot.
 */
public class Ui {

    private String wrapMessage(String message) {
        String line = "----------------------------------------------";
        return line + "\n " + message + "\n" + line;
    }

    /**
     * Greets the user when they start up the Duke chat bot.
     */
    public String greet() {
        String greeting = "Hello! I'm Duke\nWhat can I do for you? (◠  ◠✿)";
        return greeting;
    }

    /**
     * Bids farewell to the user when they exit the Duke chat bot.
     * @return A farewell message.
     */
    public String exit() {
        String byeMessage = "Bye! ( ´ ▽ ` )/";
        System.exit(0);
        return byeMessage;
    }

    /**
     * Informs the user that the task has been added to the list and the number of tasks
     * currently in the list.
     *
     * @param task The task to be added.
     * @param size The number of tasks in the current list.
     */
    public String addTask(Task task, int size) {
        String message = "Got it. I've added this task: \n  " + task +
                "\n Now you have " + size + " tasks in the list.";
        return message;
    }

    /**
     * Informs the user that the task has been marked as done.
     *
     * @param task The task to be completed.
     * @return A message to inform the user that the task has been marked as done.
     */
    public String completeTask(Task task) {
        String message = "Nice! I've marked this task as done: \n  " + task;
        return message;
    }

    /**
     * Informs the user that the task has been deleted and the number of tasks currently
     * in the list.
     *
     * @param task The task to be deleted.
     * @param size The number of tasks in the current list.
     */
    public String deleteTask(Task task, int size) {
        String message = "Noted. I've removed this task: \n " + task +
                "\n Now you have " + size + " tasks in the list.";
        return message;
    }

    /**
     * Lists out all the tasks in the current task list in
     * numbered order.
     *
     * @param formattedList The formatted task list.
     * @return The list of tasks.
     */
    public String list(String formattedList) {
        assert formattedList != null;
        String list = "Here are the tasks in your list: \n  ";
        list += formattedList;
        return list;
    }

    /**
     * Informs the user of the error that has occurred while using the Duke
     * chat bot.
     *
     * @param e The error caught.
     */
    public String showDukeError(DukeException e) {
        return e.toString();
    }

    /**
     * Informs the user that an error occurred while trying to save their tasks.
     */
    public String showSaveError() {
        return "Something went wrong while saving your tasks...";
    }

    /**
     * Informs the user that an error occurred while trying to load their tasks.
     */
    public String showLoadError() {
        return wrapMessage("Something went wrong while loading your tasks...");
    }

    public String find(String matchingTasks) {
        assert matchingTasks != null;
        String list = "Here are the matching tasks in your list: \n  ";
        list += matchingTasks;
        return list;
    }
}
