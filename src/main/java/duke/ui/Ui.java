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
     * Makes Duke say the given String.
     *
     * @param string the String for Duke to say.
     */
    public void say(String string);

    /**
     * Sets the function for handling user input. The Ui will call this function whenever it receives user input.
     *
     * @param function The function for handling user input, which should take in a single String, the user input.
     */
    public void setInputHandler(Consumer<String> function);
}
