package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates a ui.
 */
public class Ui {
    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String logo = "      ___                __     \n"
                + "     |  _ \\   ____      |  |    \n"
                + "     | |_| | / __  \\  __|  |__  \n"
                + "     |  _ / | |  |  ||__    __| \n"
                + "     | |    | |_/   |   |  |    \n"
                + "     |_|     \\___/|_|   |__|    \n";
        String[] greetingTexts = {"Hello! I'm Pat", "What can I do for you?"};
        Response greeting = new Response(greetingTexts);
        System.out.println("     Hello from\n" + logo);
        System.out.println(greeting.getResponse());
        showLine();
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
     * Prints the message of adding a task.
     * 
     * @param task The task to be added.
     * @param tasks The task list.
     */
    public void showAddMessage(Task task, TaskList tasks) {
        Response msg = new Response(new Task[]{task}, Response.Tag.ADD, tasks.size());
        System.out.println(msg.getResponse());
    }

    /**
     * Prints the message of deleting a task.
     * 
     * @param task The task to be deleted.
     * @param tasks The task list.
     */
    public void showDeleteMessage(Task task, TaskList tasks) {
        Response msg = new Response(new Task[]{task}, Response.Tag.REMOVE, tasks.size());
        System.out.println(msg.getResponse());
    }

    /**
     * Prints the list of the tasks.
     * 
     * @param tasks The task list.
     */
    public void listTasks(TaskList tasks) {
        Response list = new Response(tasks.getArray(), Response.Tag.LIST);
        System.out.println(list.getResponse());
    }

    /**
     * Prints the done message when marking a task as done.
     * 
     * @param task The task to be marked done.
     */
    public void showDoneMessage(Task task) {
        Response msg = new Response(new String[]{"Nice! I've marked this task as done:", "  " + task});
        System.out.println(msg.getResponse());
    }

    /**
     * Prints the list of tasks that match the searching keyword.
     * 
     * @param tasks The tasks that match the searching keyword.
     */
    public void showFindMessage(ArrayList<Task> tasks) {
        Response msg = new Response(tasks.toArray(new Task[0]), Response.Tag.FIND);
        System.out.println(msg.getResponse());
    }

    /**
     * Prints the loading error when loading the local file.
     */
    public void showLoadingError() {
        Response msg = new Response(new String[]{"Folder or file does not exist yet! Please make sure you have data/duke.txt in ip directory. "});
        System.out.println(msg.getResponse());
    }

    /**
     * Prints the error message.
     * 
     * @param errMessage The error message.
     */
    public void showError(String errMessage) {
        Response msg = new Response(new String[]{errMessage});
        System.out.println(msg.getResponse());
    }

    /**
     * Prints the bye message when exiting the program.
     */
    public void showByeMessage() {
        Response msg = new Response(new String[]{"Bye. Hope to see you again soon!"});
        System.out.println(msg.getResponse());
    }
    
}
