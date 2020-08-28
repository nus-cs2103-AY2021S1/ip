package duke.ui;

// Class that handle the printing and formatting of the program's response message.
public class Ui {
    private final static String DIVIDER = "____________________________________________________________";
    private final static String ERROR_HEADER = "___________________________ERROR!___________________________";

    /**
     * Prints a formatted response message to the user.
     *
     * @param msg String message to show the user
     */
    public void print(String msg) {
        System.out.print(DIVIDER + "\n" + msg + "\n" + DIVIDER + "\n");
    }

    /**
     * Prints a formatted error message to the user.
     *
     * @param msg String error message to show the user
     */
    public void printErr(String msg) {
        System.out.print(ERROR_HEADER + "\n" + msg + "\n" + DIVIDER + "\n");
    }
}
