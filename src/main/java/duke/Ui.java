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

    private static final String START_MESSAGE = "Whassup I'm Gilth Sebert,\nyour friendly task manager!\nWhatchu wanna do today?";
    private static final String END_MESSAGE = "Bye bye and remember to study hard!";

    private final Scanner sc;


    /**
     * Creates a new Ui object.
     * Default to System.in as input stream.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }


    /**
     * Creates a bew Ui object.
     * Overrides default input stream.
     *
     * @param sys Input stream to override default input stream.
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
        return Ui.END_MESSAGE;
    }


    /**
     * Prints the start message.
     */
    public void printStartMessage() {
        String startMsg = Ui.getStartMessage();
        this.printMessage(startMsg);
    }


    /**
     * Prints the end message.
     */
    public void printEndMessage() {
        String endMessage = Ui.getEndMessage();
        this.printMessage(endMessage);
    }


    /**
     * Prints a separator.
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
        assert msg != null;
        System.out.println(msg);
        Ui.printSeparator();
    }


    /**
     * Prints empty index error message.
     *
     * @param commandStr String representation of command.
     * @return Error string printed.
     */
    public String printEmptyIndexErrorMsg(String commandStr) {
        assert commandStr != null;
        String errorMsg = String.format("OOPS!!! The index of `%s` cannot be empty.", commandStr);
        this.printErrorMessage(errorMsg);
        return errorMsg;
    }


    /**
     * Prints invalid index error message.
     *
     * @return Error string printed.
     */
    public String printInvalidIndexErrorMsg() {
        String errorMsg = "OOPS!!! The index given is invalid.";
        this.printErrorMessage(errorMsg);
        return errorMsg;
    }


    /**
     * Prints generic error message.
     *
     * @param errMsg Error message to be printed.
     * @return Error string printed.
     */
    public String printErrorMessage(String errMsg) {
        assert errMsg != null;
        this.printMessage(errMsg);
        return errMsg;
    }

}
