package main.java;

/**
 * Prints necessary output to user.
 */
public class Ui {
    public final static String LINE = "____________________________________________________________\n";

    /**
     * Introduces bot with welcome message.
     */
    public static void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String INITIAL_PRINTING = logo + "Hello! I'm Duke\n" + "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(INITIAL_PRINTING);
    }

    /**
     * Signs off with Bye message.
     */
     public static void showByeMessage() {
         System.out.println("Byeeeee see you later!\n" + LINE);
     }

    /**
     * Prints message to user.
     * @param message the message to be printed.
     */
    public static void printMessageToUser(String message) {
        System.out.println(message);
    }

    /**
     * Prints message that signals the deletion of a task.
     */
    public static void printDeleteTaskMessage() {
        System.out.println("I have removed the task from your list.\n" + LINE);
    }

    /**
     * Prints message that signals the completion of a task.
     */
    public static void printDoneMessage(boolean isDone) {
        if (isDone) {
            System.out.println("Task is already done!\n" + LINE);
        } else {
            System.out.println("Congratulations! I have marked this task done.\n" + LINE);
        }
    }
}
