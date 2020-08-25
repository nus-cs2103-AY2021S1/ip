package duke;

import java.util.Scanner;

/**
 * Standard user-interface class that handles interactions between the user and the engine.
 */
public class Ui {

    //Constants used when formatting the bot's message
    protected static final String LINE_BREAK = "    ____________________________________________________________\n";
    protected static final String PRESPACING = "     ";

    /** Standard opening message sent */
    protected static final String OPENING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";

    //Scanner object used to obtain response from the System
    private Scanner scanner;

    /**
     * Standard constructor that initialises a UI object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Queries the user for the next command in the form of a string.
     * @return string provided by the user
     */
    public String getCommand() {
        return scanner.nextLine();
    }

    /**
     * Notifies the user with the given message
     * @param message message to be sent
     */
    public void outputMessage(String message) {
        System.out.println(processString(message));
    }

    /**
     * Message sent to user when the bot starts up.
     */
    public void startup() {
        outputMessage(getOpeningText());
    }

    /**
     * String formatter that customises messages to be sent to the user.
     * @param string message to be sent
     * @return formatted message that will be sent
     */
    protected static String processString(String string) {
        return LINE_BREAK  + PRESPACING
                + string.replaceAll("\n", '\n' + PRESPACING)
                + '\n' + LINE_BREAK;
    }


    /** Method used to obtain the opening text when the bot starts up */
    private String getOpeningText() {
        return OPENING_MESSAGE;
    }



}
