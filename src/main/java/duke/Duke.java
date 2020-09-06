package duke;

import duke.command.Command;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static Duke createDuke() {
        return new Duke("data/tasks.txt");
    }

    private Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        }
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
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
