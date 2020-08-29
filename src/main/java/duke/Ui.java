package duke;

public class Ui {
    private static final String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";

    /**
     * Returns a greeting message
     *
     * @return a greeting message
     */
    public static String greet() {
        return "Hello! This is Duke.\nWhat can I do for you?";
    }

    /**
     * Returns an exiting message
     *
     * @return an exiting message
     */
    public static String exit() {
        return "Bye. Duke is always there for you!";
    }

    /**
     * Returns an error message of command
     *
     * @return an error message
     */
    public static String commandError() {
        return "Sorry, I do not know what that means :(";
    }

    /**
     * Returns an error message of file
     *
     * @return an error message
     */
    public static String fileError() {
        return "Oops! Something went wrong :(";
    }

    /**
     * Returns an error message of list
     *
     * @return an error message
     */
    public static String listError() {
        return "There is no task in the list :)";
    }

    /**
     * Returns a given message
     *
     * @param msg the message given
     * @return the message given
     */
    public static String userMessage(String msg) {
        return msg;
    }

}
