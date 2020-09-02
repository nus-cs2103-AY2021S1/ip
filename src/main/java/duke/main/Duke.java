package duke.main;

import duke.ui.Ui;

/**
 * This is the main class that
 * is directly called by the users.
 */
public class Duke {
    private Ui ui;

    /**
     * Initializes Duke object.
     */
    public Duke() {
        this.ui = new Ui();
    }

    /**
     * Gets the Ui object in Duke.
     * @return The Ui object.
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Returns the response after user input.
     * @param input String of user input.
     * @return The response of the user input.
     */
    public String getResponse(String input) {
        return ui.getResponse(input);
    }

}
