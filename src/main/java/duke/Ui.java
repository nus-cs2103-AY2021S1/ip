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
     * Prints start message.
     */
    public static void printStartMessage() {
        Ui.printSeparator();
        // System.out.println(Ui.LOGO);
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        Ui.printSeparator();
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


    public void printEmptyIndexErrorMsg(String commandStr) {
        this.printErrorMessage(String.format("OOPS!!! The index of `%s` cannot be empty.", commandStr));
    }


    public void printInvalidIndexErrorMsg() {
        this.printErrorMessage("OOPS!!! The index given is invalid.");
    }


    public void printErrorMessage(String errMsg) {
        this.printMessage(errMsg);
    }

}
