package duke.core;

import java.util.Scanner;

/**
 * An object used to receive User input during the usage of the Duke programme.
 */
public class Ui {

    private boolean active;
    private Scanner userInput;

    /**
     * Public no-argument constructor for Ui.
     * Initializes the Ui object to be active and assigns a scanner to
     * receive user input.
     */
    public Ui() {
        active = true;
        userInput = new Scanner(System.in);
    }

    /**
     * No-argument method to check the status of this Ui object
     * @return a boolean value indicating the status (active or inactive) of this Ui object
     */
    public boolean isActive() {
        return active;
    }

    /**
     * No-argument method for this Ui object to receive user input.
     * @return a string that represents the user input
     */
    public String receiveInput() {
        String input = userInput.nextLine();
        return input;
    }

    /**
     * No-argument method to deactivate this Ui object.
     * This is used to terminate the Duke programme.
     * @see Duke
     */
    public void setInactive() {
        active = false;
    }
}
