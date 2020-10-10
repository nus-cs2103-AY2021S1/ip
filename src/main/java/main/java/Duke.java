package main.java;

/**
 * Represents a robot who can help the user to make todo list.
 * A <code>Duke</code> object is an instance of such robots.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("./command.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        Parser parser = new Parser(tasks, true);
        return parser.handleCommand(input);
    }
}

