package duke.util;

import duke.task.Task;

import java.util.Scanner;
import java.util.List;

/**
 * User interface (UI) handles all the message formatting for the bot's
 * personality, and also printing of messages to the command line interface (CLI).
 * All bot messages and response go through this Ui to reach the user.
 */
public class Ui {

    private final Scanner sc;
    private final String LINE = "    --------------------------------------------------------------";
    private final String WELCOME_MESSAGE = "Hello! Tebby lives to serve :)\nSend 'help' for list of commands!";

    /**
     * Constructs the Ui and scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the logo of the application along with the greeting message.
     */
    public void showWelcome() {
        String logo = "                                                 _     _\n"
                + " _______  _______  _______  _______  __   __    (c).-.(c)\n"
                + "|       ||       ||  _    ||  _    ||  | |  |    / ._. \\ \n"
                + "|_     _||    ___|| |_|   || |_|   ||  |_|  |  __\\( Y )/__\n"
                + "  |   |  |   |___ |       ||       ||       | (_.-/'-'\\-._)\n"
                + "  |   |  |    ___||  _   | |  _   | |_     _|    ||   || \n"
                + "  |   |  |   |___ | |_|   || |_|   |  |   |    _.' `-' '._\n"
                + "  |___|  |_______||_______||_______|  |___|   (.-./`-'\\.-.)\n"
                + "                                               `-'     `-'\n"
                + "The Task Manager";
        System.out.println(logo);
        sendMessage(WELCOME_MESSAGE);
    }

    /**
     * Prints the exit message.
     */
    public void showExit() {
        String msg = LINE + "\n    Have a good day! Tebby logging off...\n";
        msg += "    ----------------------------X---------------------------------";
        System.out.println(msg);
    }

    /**
     * Prints the bot's message in a formatted way.
     * The standardized format for printing messages from the bot is:
     *
     *     --------------------------------------------------------------
     *     Bot's message here, Line 1
     *     Line 2
     *     --------------------------------------------------------------
     * @param message the message of the bot to be formatted.
     */
    public void sendMessage(String message) {
        StringBuilder sb = new StringBuilder(message);
        int offset = 0;
        while (true) {
            int nextIndex = sb.indexOf("\n", offset);
            if (nextIndex != -1) {
                sb.insert(nextIndex + 1, "    ");
                offset = nextIndex + 5;
            } else {
                break;
            }
        }
        System.out.println(LINE + "\n    " + sb + "\n" + LINE);
    }

    /**
     * Prefixes the scanner input with ">>" as a
     * visual indication for the user to type an input. Example:
     *
     * >> User input here
     *
     * @return the entire line of user input.
     */
    public String readCommand() {
        System.out.print(">> ");
        return sc.nextLine();
    }

    /**
     * Returns the message for reporting the number of tasks in the list.
     * The message considers zero, singular, and plural number of tasks.
     * @param size the size of the list.
     * @return the task report message.
     */
    public String getTaskReportMessage(int size) {
        String task = size == 1 ? "task" : "tasks";
        String num = size == 0 ? "no" : String.valueOf(size);
        return "now you have " + num + " " + task + " in the list!";
    }

    /**
     * Returns the message after a task manipulation command has been
     * successfully processed.
     * @param type the type of task manipulation.
     * @param task the task object to be added to the message.
     * @return the formatted success message.
     */
    public String getTaskSuccessMessage(String type, Task task) throws DukeException {
        StringBuilder sb = new StringBuilder("Okay I've ");
        assert List.of("add", "remove", "done", "start").contains(type)
                : "Task manipulation type can only be one of [add, remove, done, start].";
        switch(type) {
        case "add":
            sb.append("added:");
            break;
        case "remove":
            sb.append("removed:");
            break;
        case "done":
            sb.append("marked this task as done:");
            break;
        case "start":
            sb.append("set this task's start date time:");
            break;
        default:
            throw new DukeException("Unrecognized task manipulation type!");
        }
        sb.append(String.format("\n    %s", task));
        return sb.toString();
    }

    /**
     * Gets the list of commands available in the application.
     * @return the list of commands in a string message.
     */
    public String getListOfCommands() {
        String[] commands = new String[]{
                "list", "todo <description>",
                "event <description> /at <date> [time]",
                "deadline <description> /by <date> [time]",
                "fixed <description> /for <duration>",
                "done <task no./all>", "remove <task no./all/done>",
                "sort <name/type/datetime>",
                "start <task no.> <date time/now>"
        };
        StringBuilder sb = new StringBuilder("Here are the available commands:\n");
        for (String command: commands) {
            sb.append(" - ").append(command).append("\n");
        }
        String additionalInfo = "Fields: <required> [optional]"
                + "\nDate: YYYY-MM-DD or today/tomorrow"
                + "\nTime: HH:MM (24 hr format)";
        sb.append("\n").append(additionalInfo).append("\n\nRefer to user guide for the complete guide :)");
        return sb.toString();
    }

    /**
     * Gets the message when clearing of list is successful.
     * @return the message.
     */
    public String getClearListMessage() {
        return "Your list has been cleared!! Yay~";
    }

    /**
     * Gets the message when removing of done tasks in the list is successful.
     * @return the message.
     */
    public String getRemoveDoneMessage() {
        return "All completed tasks have been removed :)";
    }
    /**
     * Gets the message when there is attempt to sort an empty list.
     * @return the message.
     */
    public String getEmptyListSortingMessage() {
        return "I can't sort an empty list :(";
    }

    /**
     * Gets the message when sorting of list is sucessful.
     * @return the message.
     */
    public String getSuccessSortingMessage() {
        return "Your list has been sorted!";
    }

    /**
     * Gets the welcome message.
     * @return the message.
     */
    public String getGuiWelcomeMessage() {
        return WELCOME_MESSAGE;
    }
}
