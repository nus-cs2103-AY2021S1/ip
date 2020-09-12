package ui;

import java.util.Scanner;

import task.Task;

/**
 * Ui class deals with everything that has to do
 * with user interface, like showing messages,
 * showing list of task, and showing exception message.
 */
public class Ui {
    static final String LINE = "___________________________________________________________________________";
    static final String DOUBLE_TAB = "      ";

    /**
     * Constructs a new Ui.
     */
    public Ui() {}

    /**
     * Reads the input command from user.
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
    public String getMessageTemplate(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(input).append("\n");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * Gets the template of exception message, where
     * the exception messages are in between two lines.
     *
     * @param exception String exception message.
     */
    public void getExceptionTemplate(Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(exception.toString()).append("\n");
        System.out.println(stringBuilder.toString());
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
        String indentation = "  |   ";
        return command + "  |   " + format + "\n";
    }

    /**
     * Gets the list of existing commands that is available
     * in duke.Duke program ans shows it to the user via HelpCommand.
     */
    public String getCommandList() {
        return formatCommandList("COMMAND", "FORMAT")
                + formatCommandList("- deadline", "deadline <DEADLINE_NAME> /by <yyyy-MM-dd> <HH:mm>")
                + formatCommandList("- delete", "delete <TASK_NUMBER>")
                + formatCommandList("- delete all", "delete all")
                + formatCommandList("- done", "done <TASK_NUMBER>")
                + formatCommandList("- done all", "done all")
                + formatCommandList("- event", "event <EVENT_NAME> /at <yyyy-MM-dd> <HH:mm>")
                + formatCommandList("- show after", "show after <yyyy-MM-dd>")
                + formatCommandList("- show before", "show before <yyyy-MM-dd>")
                + formatCommandList("- todo", "todo <TASK_NAME>");
    }

    /**
     * Indents the inputted message by two tabs.
     *
     * @param input String input.
     * @return String indented message.
     */
    public String formatMessage(String input) {
        return DOUBLE_TAB + input;
    }

    /**
     * Indents the inputted exception message by two tabs.
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
    public String greet() {
        return getMessageTemplate("Hello! I'm Rich.\n"
                + "What can I do for you?");
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
    public String getTaskMessage(Task task, int size) {
        return getMessageTemplate("Got it. I've added this task :\n" + task + "\n"
                + "Now you have " + size + " tasks in the list");
    }
}
