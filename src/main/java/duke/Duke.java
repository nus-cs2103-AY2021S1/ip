package duke;

import duke.command.Command;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Statistics stats;

    private Duke(String filePathTasks, String filePathStats) {
        ui = new Ui();
        try {
            storage = new Storage(filePathTasks, filePathStats);
        } catch (DukeException e) {
            ui.showError(e.toString());
        }
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        }
        try {
            stats = new Statistics(storage.loadStats());
        } catch (DukeException e) {
            ui.showError(e.toString());
            stats = new Statistics();
        }
    }

    public static Duke createDuke() {
        return new Duke("data/tasks.txt", "data/stats.txt");
    }

    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Generates Duke's response to user input.
     * @param input A string representing the user input from text box.
     * @return A string representing Duke's response to user input.
     */
    public String getResponse(String input) {
        try {
            // interpret user input
            Command c = Parser.parse(input);
            assert tasks != null : "task list should not be uninitialised";
            assert ui != null : "ui should not be uninitialised";
            assert storage != null : "storage should not be uninitialised";
            return c.execute(tasks, storage, stats);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
