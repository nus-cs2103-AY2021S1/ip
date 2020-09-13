package duke.ui;

/**
 * Class to handle and abstract Ui (currently stdio) operations.
 */
public interface Ui {
    /**
     * Queries if the Ui is active or not.
     * @return true if ui object is active.
     */
    boolean isActive();

    /**
     * Used to activate the Ui object, sending the welcome
     * message and allowing it to use its I/O functions.
     * Sets isActive to true.
     */
    void start();

    /**
     * Deactivates the Ui object and disables its I/O functions.
     * Sets isActive to false.
     */
    void close();

    /**
     * Returns the next line entered by the user.
     * @return String of user input.
     */
    String nextLine();

    /**
     * Outputs a message to the user. Output is differentiated
     * from user input.
     * @param input to be sent to the user.
     */
    void systemMessage(String input);

}
