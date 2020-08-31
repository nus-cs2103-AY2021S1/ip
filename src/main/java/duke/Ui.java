package duke;

import java.util.Scanner;

/**
 * Standard user-interface class that handles interactions between the user and the engine.
 */
public abstract class Ui {


    /** Standard opening message sent */
    protected static final String OPENING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";


    /**
     * Queries the user for the next command in the form of a string.
     * @return string provided by the user
     */
    public abstract String getResponse();

    /**
     * Notifies the user with the given message
     * @param message message to be sent
     */
    public abstract void outputMessage(String message);

    /**
     * Sends a default message to the user upon startup
     */
    public void startup() {
        outputMessage(getOpeningText());
    }

    /** Method used to obtain the opening text when the bot starts up */
    private String getOpeningText() {
        return OPENING_MESSAGE;
    }



}
