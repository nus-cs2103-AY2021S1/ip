package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * A class that handles all of user's and duke's interactions.
 */
public class Ui {
    private static String duke = "Duke> ";
    private static String cmd = ">> ";
    private static String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    /**
     * Constructor to create a scanner object for user inputs.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a startup message with Duke's logo and greeting.
     */
    public void startupMsg() {
        System.out.println(logo);
        System.out.println(duke + "Hi I'm Duke!");
        System.out.println(duke + "What can I do for you?");
    }

    /**
     * A method to read user input using scanner object. Prints a shell-like prompt to indicate
     * waiting for user input.
     *
     * @return String from user input.
     */
    public String readInput() {
        System.out.print(cmd);
        return sc.nextLine();
    }

    /**
     * Show Duke's commands available to the user.
     */
    public void showHelp() {
        String s = "Here's what I can do:\n";
        String msg =
                "Available Commands: \n"
                        + "'todo' \n"
                        + "'deadline' \n"
                        + "'event' \n"
                        + "'list' \n"
                        + "'delete' \n"
                        + "'bye'";
        System.out.println(s + msg);
    }

    /**
     * Displays task list into readable format.
     *
     * @param list TaskList object.
     */
    public void showTaskList(TaskList list) {
        int idx = 0;
        for (Task t : list.getList()) {
            System.out.println(++idx + ". " + t.toString());
        }
    }

    /**
     * Displays the relevant error message when an exception is caught.
     *
     * @param e Caught Exception.
     */
    public void showErrorMsg(Exception e) {
        System.out.println(duke + e.getMessage());
    }

    /**
     * A message to user to indicate the task list is empty.
     */
    public void showListEmptyMsg() {
        System.out.println(duke + "Your List is Empty.");
    }

    /**
     * A header message to indicate the task list below.
     */
    public void showListMsg() {
        System.out.println(duke + "Here's your Task List:");
    }

    /**
     * A message to user to prompt for input.
     */
    public void showListDoneAskMsg() {
        System.out.println(duke + "Choose the task(s) to be marked as 'Done'");
    }

    /**
     * A message to user that the tasks are marked done successfully.
     */
    public void showListDoneMsg() {
        System.out.println(duke + "Nice! I've marked the following as done:");
    }

    /**
     * A message to user to prompt for input.
     */
    public void showTaskAddAskMsg() {
        System.out.println(duke + "Enter task details:");
    }

    /**
     * A message to user that the tasks are added into the list successfully.
     */
    public void showTaskAddedMsg(Task t) {
        System.out.println(duke + "I've added '" + t.getTaskName() + "' to your Task List");
    }

    /**
     * A message to user to prompt for input.
     */
    public void showTaskDeleteAskMsg() {
        System.out.println("Choose the task(s) to be deleted.");
    }

    /**
     * A message to user that the tasks are deleted from the list successfully.
     */
    public void showTaskDeleteMsg() {
        System.out.println(duke + "I've deleted the task(s) you specified:");
    }

    /**
     * A message when the user quits duke using the command 'bye'.
     */
    public void showByeMsg() {
        System.out.println(duke + "See you soon!");
    }
}
