package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class that deals with all interactions with the user.
 */
public class Ui {
    private static final String MESSAGE_GREETING = "Hey kid,\n" + "How can grandpa help you?";
    private static final String MESSAGE_FAREWELL = "Come visit again soon!";
    private static final String MESSAGE_DONE = "Good job kid!";
    private static final String MESSAGE_ADD_TASK = "Got it, I've added this task:";
    private static final String MESSAGE_NUMBER_OF_TASKS = "You still have %d tasks left.";
    private static final String MESSAGE_REMOVE_TASK = "Alright, I've removed this task:";
    private static final String MESSAGE_LIST = "Here are your tasks:";
    private static final String MESSAGE_FIND = "Were these what you were looking for?";
    private static final String MESSAGE_ARCHIVE = "Got it, I've saved them somewhere else.";
    private Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message when starting Duke.
     * @return A string containing the greeting message.
     */
    public String greet() {
        return MESSAGE_GREETING;
    }

    /**
     * Prints the goodbye message when stopping Duke.
     * @return A string containing the goodbye message.
     */
    public String farewell() {
        return (MESSAGE_FAREWELL);
    }

    /**
     * Prints the message when marking a task as done.
     * @param doneTask the Task marked as done.
     * @return A string indicating that the task is marked as done.
     */
    public String doneText(Task doneTask) {
        return (MESSAGE_DONE + "\n" + doneTask);
    }

    /**
     * Prints the message when adding a task to the list.
     * @param addTask the Task added to the list.
     * @param result the TaskList the task is added to.
     * @return A string indicating that the task is added.
     */
    public String addTaskText(Task addTask, TaskList result) {
        return (MESSAGE_ADD_TASK + "\n" + addTask
                + "\n" + String.format(MESSAGE_NUMBER_OF_TASKS, result.getSize()));
    }

    /**
     * Prints the message when deleting a task from the list.
     * @param deleteTask the Task deleted from the list.
     * @param result the TaskList the task is deleted from.
     * @return A string indicating that the selected task is deleted.
     */
    public String deleteTaskText(Task deleteTask, TaskList result) {
        return (MESSAGE_REMOVE_TASK + "\n" + deleteTask
                + "\n" + String.format(MESSAGE_NUMBER_OF_TASKS, result.getSize()));
    }
    /**
     * Prints all the tasks in the list.
     * @param tasks the TaskList from which all the tasks should be printed from.
     * @return A string containing all tasks in the list.
     */
    public String listText(TaskList tasks) {
        return MESSAGE_LIST + "\n" + tasks.iterateList();
    }

    /**
     * Prints all tasks containing the keyword from the list.
     * @param tasks the TaskList from which all relevant tasks are taken from.
     * @param keyword the keyword used to determine if a task is relevant.
     * @return A string containing all relevant tasks from the list.
     */
    public String listRelevantTasks(TaskList tasks, String keyword) {
        return MESSAGE_FIND + "\n" + tasks.iterateFind(keyword);
    }

    /**
     * A function to print an error message for the user.
     * @param e the error message.
     * @return A string representing the error message.
     */
    public String printError(Exception e) {
        return e.toString();
    }

    /**
     * A function to scan the user's next input and convert it to lower case.
     * @return a string representing the user's input.
     */
    public String readCommand() {
        return sc.nextLine().toLowerCase();
    }

    public String archiveText() {
        return MESSAGE_ARCHIVE;
    }
}
