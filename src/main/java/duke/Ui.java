package duke;

import java.util.Scanner;

/**
 * Standard user-interface class that handles interactions between the user and the engine.
 */
public interface Ui {

    /** Standard opening message sent */
    public static final String OPENING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";

    /**
     * Queries the user for the next command in the form of a string.
     * @return string provided by the user
     */
    public String getInput();

    /**
     * Notifies the user with the given message.
     * @param message message to be sent
     */
    public void outputMessage(String message);

    /**
     * Initialises Ui.
     */
    public void startup();


    public void exit();


}
