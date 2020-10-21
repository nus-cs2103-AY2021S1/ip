package duke;

import duke.command.Command;

/**
 * A chat-bot with the ability to perform certain list management actions commanded by the user.
 */
public class Duke {

    /** The user interface object for interacting with the user. */
    private Ui ui;
    /** The storage object for loading or storing a Duke-list in the form of a text file */
    private Storage storage;

    /**
     * Constructs a new Duke object ready to start a new session or resume a previous one.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
    }

    /**
     * Responds to the user input, if it can be processed. Otherwise, alerts the user to the error
     * that occurred.
     *
     * @param input The input from the user.
     * @return The response, or the alert to the error that occurred.
     */
    String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(ui, storage);
            return ui.getMessage();
        } catch (DukeException e) {
            return ui.addErrorPrefix(e.getMessage());
        }
    }

}

