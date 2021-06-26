package duke.ui;

import java.util.function.Consumer;

/**
 * This interface defines how other classes (eg. Commands, TaskListStorage) can manipulate the Ui.
 */
public interface Ui {

    /**
     * Tells Duke to exit after it finishes handling the current user input.
     */
    public void stop();

    /**
     * Makes Duke say the given message. If the message is an error, it will be formatted differently.
     *
     * @param string Message for Duke to say.
     * @param isError Whether this message is an error.
     */
    public void say(String string, boolean isError);

    /**
     * Makes Duke say the given non-error message.
     *
     * @param string Message for Duke to say.
     */
    public default void say(String string) {
        say(string, false);
    }

    /**
     * Sets the function for handling user input. The Ui will call this function whenever it receives user input.
     *
     * @param function Function for handling user input, which should take in a single String, the user input.
     */
    public void setInputHandler(Consumer<String> function);
}
