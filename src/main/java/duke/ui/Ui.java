package duke.ui;

/**
 * Prints necessary output to user.
 */
public class Ui {
    /**
     * Introduces bot with welcome message.
     */
    public static String printWelcomeMessage() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String initialPrinting = logo + "Hello! I'm Duke\n" + "What can I do for you?\n"
                + "____________________________________________________________\n";
        return (initialPrinting);
    }

    /**
     * Signs off with Bye message.
     */
    public static String printByeMessage() {
        return ("Byeeeee see you later!\n");
    }

    /**
     * Prints message to user.
     *
     * @param message the message to be printed.
     */
    public static String printMessage(String message) {
        return message;
    }

    /**
     * Prints message that signals the deletion of a task.
     */
    public static String printDeleteTaskMessage() {
        return ("I have removed the task from your list.");
    }

    /**
     * Prints message that signals the completion of a task.
     */
    public static String printDoneMessage(boolean isDone) {
        if (isDone) {
            return ("Task is already done!\n");
        } else {
            return ("Congratulations! I have marked this task done.\n");
        }
    }

    public static String printErrorMessage(String message) {
        return (message);
    }

}
