package duke.logic;

public class Ui {
    /**
     * Returns the string as passed into it.
     * @param msg the message to be returned.
     * @return the message passed in.
     */
    private static String printMessage(String msg) {
        return msg;
    }

    /**
     * Returns the initial message sent when opening the program.
     * @return the message that is shown at the start of the program.
     */
    public static String initialMessage() {
        String toPrint = "Hello! I'm Duke the Bad Dragon.\n" + "What can I do for you?\n";
        return printMessage(toPrint);
    }

    /**
     * Returns the initial message sent when closing the program.
     * @return the message that is shown at the end of the program.
     */
    public static String exitMessage() {
        return printMessage("Bye. Hope to see you again soon!\n");
    }

    /**
     * Returns the message when a task is marked as done.
     * @param msg the String representation of the task, to be printed.
     * @return the message that is shown when a task is marked as done.
     */
    public static String printDone(String msg) {
        String toPrint = "Nice! I've marked this task as done:" + "\n" + msg;
        return printMessage(toPrint);
    }

    /**
     * Returns the message when the list needs to be printed.
     * @param msg the String representation of the list, to be printed.
     * @return the message that is shown when the list needs to be printed.
     */
    public static String printList(String msg) {
        String toPrint = "Here are the tasks in your list:\n" + msg;
        return printMessage(toPrint);
    }

    /**
     * Returns the message when a task is added.
     * @param description the String description of the task added.
     * @param numberOfTasks the number of tasks in the list.
     * @return the message that is shown when a task is added.
     */
    public static String printAdd(String description, int numberOfTasks) {
        String toPrint = "Got it. I've added this task:\n"
                + description + "\n"
                + "Now you have " + numberOfTasks;
        if (numberOfTasks == 1) {
            toPrint = toPrint + " task in the list.\n";
        } else {
            toPrint = toPrint + " tasks in the list.\n";
        }
        return printMessage(toPrint);
    }

    /**
     * Returns the message when a task is deleted.
     * @param description the String description of the task deleted.
     * @param numberOfTasks the number of tasks in the list.
     * @return the message that is shown when a task is added.
     */
    public static String printDelete(String description, int numberOfTasks) {
        String toPrint = "Noted. I've removed this task: \n"
                + description + "\n"
                + "Now you have " + numberOfTasks;
        if (numberOfTasks == 1) {
            toPrint = toPrint + " task in the list.\n";
        } else {
            toPrint = toPrint + " tasks in the list.\n";
        }
        return printMessage(toPrint);
    }

    /**
     * Returns the help message.
     * @return the help message.
     */
    public static String printHelpMessage() {
        String toPrint = "Hi!\n"
                + "For information on the commands and formats, please visit https://theodoreleebrant.github.io/ip/";
        return printMessage(toPrint);
    }
}
