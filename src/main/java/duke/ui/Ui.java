package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * A class that handles all of user's and duke's interactions.
 */
public class Ui {
    private static final String duke = "Duke> ";
    private static final String cmd = ">> ";
    private static final String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner scanner;
    private String guiOutput;

    /**
     * Constructor to create a scanner object for user inputs.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a startup message with Duke's logo and greeting for GUI.
     */
    public String guiStartupMsg() {
        String output = "";
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
        String s;
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
                        + "'todo' - Adds a todo task.\n"
                        + "'deadline' - Adds a task with deadline.\n"
                        + "'event' - Adds an event task.\n"
                        + "'list' - Shows the task list.\n"
                        + "'done' - Select task(s) to be marked as done in the list.\n"
                        + "'delete' - Delete task(s) from task list.\n"
                        + "'find' - Find task(s) matching the keyword.\n"
                        + "'date' - Find task(s) matching the date.\n"
                        + "'help' - Show the help list with the available commands.\n"
                        + "'bye' - Exit Duke.";
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
     * A message to user that the tasks are marked done successfully.
     */
    public String showListDoneMsg() {
        return duke + "Nice! I've marked the following as done:";
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
     * A header message to indicate the 'find'/'date' results.
     */
    public String showFoundMsg(String input) {
        return duke + "Here are the task(s) matching: " + input;
    }

    /**
     * A header message to indicate no 'find'/'date' results.
     */
    public String showNotFoundMsg(String input) {
        return duke + "Found no task(s) matching: " + input;
    }


    /**
     * A method that saves Duke's response to be displayed in Duke dialog box.
     *
     * @param output Duke's response after command execution.
     */
    public void setGuiOutput(String output) {
        guiOutput = output;
    }

    /**
     * A method that returns Duke's response as a string for Duke dialog box.
     *
     * @return A string that contains the Duke's response to be shown in the GUI dialog box.
     */
    public String getGuiOutput() {
        return guiOutput;
    }

}
