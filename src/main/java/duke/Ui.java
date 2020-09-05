package duke;


import java.io.InputStream;
import java.util.Scanner;


/**
 * Ui for Duke.
 * Reads user inputs and prints messages.
 */
public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String START_MESSAGE = "Hello! I'm Duke!\nWhat can I do for you?";
    private static final String END_MESSAGE = "Bye. Hope to see you again soon!";

    private final Scanner sc;


    /**
     * Constructor for Ui.
     * Default to System.in as input stream.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }


    /**
     * Constructor for Ui.
     *
     * @param sys input stream.
     */
    public Ui(InputStream sys) {
        this.sc = new Scanner(sys);
    }


    /**
     * Gets the start message for Duke.
     *
     * @return Start message string.
     */
    public static String getStartMessage() {
        return Ui.START_MESSAGE;
    }


    public static String getEndMessage() {
        return Ui.getEndMessage();
    }


    /**
     * Prints start message.
     */
    public void printStartMessage() {
        String startMsg = Ui.getStartMessage();
        this.printMessage(startMsg);
    }


    /**
     * Prints end message.
     */
    public void printEndMessage() {
        String endMessage = Ui.getEndMessage();
        this.printMessage(endMessage);
    }


    /**
     * Prints separator.
     */
    private static void printSeparator() {
        System.out.println("____________________________________________________________");

    }


    /**
     * Checks if scanner has next line.
     *
     * @return if scanner has next line.
     */
    public boolean hasNextLine() {
        return sc.hasNextLine();
    }


    /**
     * Gets next line of text.
     *
     * @return next line of text.
     */
    public String nextLine() {
        return sc.nextLine();
    }


    /**
     * Prints given message string.
     *
     * @param msg message to be printed.
     */
    public void printMessage(String msg) {
        Ui.printSeparator();
        System.out.println(msg);
        Ui.printSeparator();
    }



    public String printEmptyIndexErrorMsg(String commandStr) {
        String errorMsg = String.format("OOPS!!! The index of `%s` cannot be empty.", commandStr);
        this.printErrorMessage(errorMsg);
        return errorMsg;
    }


    public String printInvalidIndexErrorMsg() {
        String errorMsg = "OOPS!!! The index given is invalid.";
        this.printErrorMessage(errorMsg);
        return errorMsg;
    }


    public String printErrorMessage(String errMsg) {
        this.printMessage(errMsg);
        return errMsg;
    }

}
