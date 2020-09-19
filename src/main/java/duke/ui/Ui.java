package duke.ui;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Class to display messages and interact with user.
 */
public class Ui {
    Scanner sc;

    /**
     * Initialises a new Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays greeting message to user.
     */
    public void sayGreeting() {
        System.out.println("Hi there, I'm TARS!\nWhat can I do for you today?");
    }

    /**
     * Displays farewell message to user.
     */
    public void sayFarewell() {
        System.out.println("Bye bye. See you again soon!");
    }

    /**
     * Reads a command from user.
     *
     * @return Command read from user.
     */
    public String getNextCommand() {
        return sc.nextLine();
    }

    /**
     * Display an error message from a DukeException to user.
     *
     * @param e Exception containing the error message.
     */
    public void showErrorMsg(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Display an error message from a String to user.
     *
     * @param errMessage Exception containing the error message.
     */
    public void showErrorMsg(String errMessage) {
        System.out.println(errMessage);
    }

    /**
     * Display a message to notify user of existing file loaded.
     */
    public void fileLoaded() {
        System.out.println("I found your saved tasks. You can view them using list.");
    }

    /**
     * Display a message to notify user a new file has been created.
     */
    public static void newFileCreated() {
        System.out.println("I have created a new log file to help me remember what you tell me!");
    }

    /**
     * Displays taskAdded message to user.
     *
     * @param newTask New Task to be added to TaskList.
     * @param taskList Current list of Tasks from user.
     */
    public void taskAdded(Task newTask, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n"
                + newTask + "\nNow you have "
                + taskList.numTask() + " task(s) in the list.");
    }

    /**
     * Displays taskDeleted message to user.
     *
     * @param removeTask Task to be removed from TaskList.
     * @param taskList Current list of Tasks from user.
     */
    public void taskDeleted(Task removeTask, TaskList taskList) {
        System.out.println("Poof! I've removed this task:\n"
                + removeTask + "\nNow you have "
                + taskList.numTask() + " task(s) in the list.");
    }

    /**
     * Displays markAsDone message to user.
     *
     * @param index Index of the Task from TaskList to be marked as done.
     * @param taskList Current list of Tasks from user.
     */
    public void markAsDone(int index, TaskList taskList) {
        System.out.println("Nice! I've marked this task as done:\n" + taskList.get(index));
    }

    /**
     * Displays a list of Tasks from the TaskList.
     *
     * @param taskList Current list of Tasks from user.
     */
    public void listTasks(TaskList taskList) {
        System.out.println("Let's see what we have here:\n");
        taskList.list();
    }
}
