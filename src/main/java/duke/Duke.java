package duke;

import duke.task.TaskList;

import duke.command.Command;

/**
 * A chat-bot with the ability to perform certain task-list management actions commanded by the user.
 */
public class Duke {

    /** The user interface object for interacting with the user. */
    private Ui ui;
    /** The storage object for loading or storing a task-list in the form of a text file */
    private Storage storage;
    /** The task-list to be used in the current session. */
    private TaskList tasks;

    /**
     * Constructs a new Duke object ready to start a new session or resume a previous one.
     */
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
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
            command.execute(tasks, ui, storage);
            return ui.getMessage();
        } catch (DukeException e) {
            return ui.addErrorPrefix(e.getMessage());
        }
    }

}

