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
    private Scanner scanner;

    /**
     * Constructor to create a scanner object for user inputs.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a startup message with Duke's logo and greeting.
     */
    public String startupMsg() {
        String output = "";
        output += logo + "\n";
        output += duke + "Hi I'm Duke! \n";
        output += duke + "What can I do for you? \n";
        return output;
    }

    /**
     * A method to read user input using scanner object. Prints a shell-like prompt to indicate
     * waiting for user input.
     *
     * @return String from user input.
     */
    public String readInput(boolean isGui) {
        String s = "";
        if (isGui) {
            s = MainWindow.getInput();
            return s;
        } else {
            System.out.print(cmd);
            return scanner.nextLine();
        }
    }

    /**
     * Show Duke's commands available to the user.
     */
    public String showHelp() {
        String s = "Here's what I can do:\n";
        String msg =
                "Available Commands: \n"
                        + "'todo' \n"
                        + "'deadline' \n"
                        + "'event' \n"
                        + "'list' \n"
                        + "'delete' \n"
                        + "'find' \n"
                        + "'bye'";
        return s + msg;
    }

    /**
     * Displays task list into readable format.
     *
     * @param tasks TaskList object.
     */
    public String showTaskList(TaskList tasks) {
        String output = "";
        int idx = 0;
        for (Task task : tasks.getList()) {
             output += ++idx + ". " + task.toString() + "\n";
        }
        return output;
    }

    /**
     * Displays the relevant error message when an exception is caught.
     *
     * @param e Caught Exception.
     */
    public String showErrorMsg(Exception e) {
        return duke + e.getMessage();
    }

    /**
     * A message to user to indicate the task list is empty.
     */
    public String showListEmptyMsg() {
        return duke + "Your List is Empty.";
    }

    /**
     * A header message to indicate the task list below.
     */
    public String showListMsg() {
        return duke + "Here's your Task List:";
    }

    /**
     * A message to user to prompt for input.
     */
    public String showListDoneAskMsg() {
        return duke + "Choose the task(s) to be marked as 'Done'";
    }

    /**
     * A message to user that the tasks are marked done successfully.
     */
    public String showListDoneMsg() {
        return duke + "Nice! I've marked the following as done:";
    }

    /**
     * A message to user to prompt for input.
     */
    public String showTaskAddAskMsg() {
        return duke + "Enter task details:";
    }

    /**
     * A message to user that the tasks are added into the list successfully.
     *
     * @param task Task Object.
     */
    public String showTaskAddedMsg(Task task) {
        return duke + "I've added '" + task.getTaskName() + "' to your Task List";
    }

    /**
     * A message to user to prompt for input.
     */
    public String showTaskDeleteAskMsg() {
        return "Choose the task(s) to be deleted.";
    }

    /**
     * A message to user that the tasks are deleted from the list successfully.
     */
    public String showTaskDeleteMsg() {
        return duke + "I've deleted the task(s) you specified:";
    }

    /**
     * A message when the user quits duke using the command 'bye'.
     */
    public String showByeMsg() {
        return duke + "See you soon!";
    }

    /**
     * A message to user to prompt for input for 'find' command.
     */
    public String showFindPromptMsg() {
        return duke + "Enter a keyword:";
    }

    /**
     * A header message to indicate the 'find' results.
     */
    public String showFoundMsg(String keyword) {
        return duke + "Here are the task(s) matching the keyword: " + keyword;
    }

    /**
     * A header message to indicate no 'find' results.
     */
    public String showNotFoundMsg(String keyword) {
        return duke + "Found no task(s) matching the keyword: " + keyword;
    }

    public void printMsg(String msg) {
        System.out.println(msg);
    }

}
