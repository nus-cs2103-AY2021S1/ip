import java.util.Scanner;

/**
 * User Interface (Ui) is the front-end of the application which the user
 * interacts with for both inputs and outputs. Majority of the chat bot's
 * personality is being portrayed onto this class. A snippet of this
 * interaction is as shown:
 *
 * >> list
 *     --------------------------------------------------------------
 *     Your list is empty!
 *     --------------------------------------------------------------
 * >> bye
 *     --------------------------------------------------------------
 *     Have a good day! Tebby logging off...
 *     --------------------------------------------------------------
 */

public class Ui {

    /** The Scanner object for user inputs */
    private final Scanner sc;

    /** The line for enclosing messages */
    private final String LINE = "    --------------------------------------------------------------";

    /**
     * Constructor for the Ui class.
     * The scanner is being initialized together with the Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the logo of the chat bot along with the greeting message.
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
                + "By: Andy Wu";
        System.out.println(logo);
        sendMessage("Hello! Tebby lives to serve :)");
    }

    /**
     * Prints the exit message before the application closes.
     */
    public void showExit() {
        String msg = LINE + "\n    Have a good day! Tebby logging off...\n";
        msg += "    ----------------------------X---------------------------------";
        System.out.println(msg);
    }

    /**
     * The standardized format for printing messages from the bot is:
     *
     *     --------------------------------------------------------------
     *     Bot's message here, Line 1
     *     Line 2
     *     --------------------------------------------------------------
     *
     * Messages are enclosed by 62 dash characters and indented by 4 spaces.
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
     * This method takes in the full raw input and will need to be parsed.
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
        return "Now you have " + num + " " + task + " in the list!";
    }

    /**
     * Returns the message after a task manipulation command has been
     * successfully processed. Task manipulations include adding of task,
     * deleting of task, and marking a task as completed.
     * @param type the type of task manipulation - add, remove, done.
     * @param task the task object to be added to the message.
     * @return the formatted success message.
     */
    public String getSuccessMessage(String type, Task task) {
        StringBuilder sb = new StringBuilder("Okay I've ");
        switch(type) {
        case "add":
            sb.append("added:");
            break;
        case "remove":
            sb.append("removed:");
            break;
        case "done":
            sb.append("marked this task as done:");
        }
        sb.append(String.format("\n    %s", task));
        return sb.toString();
    }

}
