package ui;

import task.Task;

import java.util.Scanner;

/**
 * Ui class deals with everything that has to do
 * with user interface, like showing messages,
 * showing list of task, and showing exception message.
 */
public class Ui {
    static final String LINE = "     ___________________________________________________________________________\n";
    static final String DOUBLE_TAB = "      ";

    /**
     * Constructs a new Ui.
     */
    public Ui() {}

    /**
     * Read the input command from user.
     *
     * @return String user command
     */
    public String readCommand() {
        enterCommand();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();;

        return command;
    }

    /**
     * Gets the template of message, where messages
     * are in between two lines.
     *
     * @param input String message.
     */
    public void getMessageTemplate(String input) {
        System.out.print(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }

    /**
     * gets the template of exception message, where
     * the exception messages are in between two lines.
     *
     * @param exception String exception message.
     */
    public void getExceptionTemplate(Exception exception) {
        System.out.print(LINE);
        System.out.println(formatExceptionMessage(exception.toString()));
        System.out.println(LINE);
    }

    /**
     * Formats the command and format message, so that
     * it follows some indentation.
     *
     * @param command String existing command.
     * @param format String command's accepted format
     * @return String Formats strings with some indentation.
     */
    public String formatCommandList(String command, String format) {
        String indentation = "%-20s%s%n" ;
        return formatMessage(String.format(indentation,command,format));
    }

    /**
     * Gets the list of existing commands that is available
     * in Duke program ans shows it to the user via HelpCommand.
     */
    public void getCommandList(){
        getMessageTemplate( formatCommandList("COMMAND", "FORMAT")
                + formatCommandList("deadline", "deadline <DEADLINE_NAME> /by <yyyy-MM-dd> <HH:mm>")
                + formatCommandList("delete", "delete <TASK_NUMBER>")
                + formatCommandList("delete all", "delete all")
                + formatCommandList("done", "done <TASK_NUMBER>")
                + formatCommandList("done all", "done all")
                + formatCommandList("event","event <EVENT_NAME> /at <yyyy-MM-dd> <HH:mm>")
                + formatCommandList("show after", "show after <yyyy-MM-dd>")
                + formatCommandList("show before", "show before <yyyy-MM-dd>")
                + formatCommandList("todo","todo <TASK_NAME>")
        );
    }

    /**
     * Indent the inputted message by two tabs.
     *
     * @param input String input.
     * @return String indented message.
     */
    public String formatMessage(String input) {
        return DOUBLE_TAB + input;
    }

    /**
     * Indent the inputted exception message by two tabs.
     *
     * @param message String exception message.
     * @return String indented exception message.
     */
    public static String formatExceptionMessage(String message) {
        return DOUBLE_TAB + message;
    }

    /**
     * Gets the greeting message and show it to the user.
     */
    public void greet() {
        getMessageTemplate(formatMessage("Hello! I'm Rich.\n")
                + formatMessage( "What can I do for you?"));
    }

    /**
     * Gets the message to indicate when program is ready to
     * receive user input.
     */
    public void enterCommand() {
        System.out.print("Enter command here: ");
    }

    /**
     * Gets the message to show specific Task
     * and the size of the task list to the user.
     * @param task Task task.
     * @param size int size of task list.
     */
    public void getTaskMessage(Task task, int size) {
        getMessageTemplate(formatMessage("Got it. I've added this task :\n")
                + formatMessage(task +"\n")
                + formatMessage("Now you have " + size + " tasks in the list"));
    }
}
