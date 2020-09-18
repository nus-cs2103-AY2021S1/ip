package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Creates a ui.
 */
public class Ui {
    /**
     * Prints the welcome message.
     */
    public String showWelcome() {
        String logo = "      ___                __     \n"
                + "     |  _ \\   ____      |  |    \n"
                + "     | |_| | / __  \\  __|  |__  \n"
                + "     |  _ / | |  |  ||__    __| \n"
                + "     | |    | |_/   |   |  |    \n"
                + "     |_|     \\___/|_|   |__|    \n";
        String[] greetingTexts = {"Hello! I'm Pat", "What can I do for you?"};
        Response greeting = new Response(greetingTexts);
        showLine();
        return "     Hello from\n"
                + logo
                + greeting.getResponse();
    }

    /**
     * A string of welcome message on GUI.
     *
     * @return String representation of welcome message on GUI.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Pat\n"
                + "What can I do for you?";
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("    __________________________________________________________ \n");
    }

    /**
     * Reads the string input.
     *
     * @return A string that will be parsed.
     */
    public String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    /**
     * Returns a string of the message of adding a task.
     *
     * @param task  The task to be added.
     * @param tasks The task list.
     */
    public String showAddMessage(Task task, TaskList tasks) {
        Response msg = new Response(new Task[]{task}, Response.Tag.ADD, tasks.size());
        return msg.getResponse();
    }

    /**
     * Returns a string of the message of deleting a task.
     *
     * @param task  The task to be deleted.
     * @param tasks The task list.
     */
    public String showDeleteMessage(Task task, TaskList tasks) {
        Response msg = new Response(new Task[]{task}, Response.Tag.REMOVE, tasks.size());
        return msg.getResponse();
    }

    /**
     * Returns a list of the tasks.
     *
     * @param tasks The task list.
     */
    public String listTasks(TaskList tasks) {
        Response list = new Response(tasks.getArray(), Response.Tag.LIST);
        return list.getResponse();
    }

    /**
     * Prints the done message when marking a task as done.
     *
     * @param task The task to be marked done.
     */
    public String showDoneMessage(Task task) {
        Response msg = new Response(new String[]{"Nice! I've marked this task as done:", "  " + task});
        return msg.getResponse();
    }

    /**
     * Returns a list of tasks that match the searching keyword.
     *
     * @param tasks The tasks that match the searching keyword.
     */
    public String showFindMessage(ArrayList<Task> tasks) {
        Response msg = new Response(tasks.toArray(new Task[0]), Response.Tag.FIND);
        return msg.getResponse();
    }

    /**
     * Returns the loading error when loading the local file.
     */
    public String showLoadingError() {
        Response msg = new Response(new String[]{"Folder or file does not exist yet! "
                + "Please make sure you have data/duke.txt in ip directory. "});
        return msg.getResponse();
    }

    /**
     * Returns a string of the error message.
     *
     * @param errMessage The error message.
     */
    public String showError(String errMessage) {
        Response msg = new Response(new String[]{errMessage});
        return msg.getResponse();
    }

    /**
     * Returns a string of the bye message when exiting the program.
     */
    public String showByeMessage() {
        Response msg = new Response(new String[]{"Bye. Hope to see you again soon!"});
        return msg.getResponse();
    }

}
