package raythx98.duke;

import raythx98.duke.command.Command;
import raythx98.duke.exception.DukeException;
import raythx98.duke.parser.Parser;
import raythx98.duke.storage.Storage;
import raythx98.duke.task.TaskList;
import raythx98.duke.ui.Ui;

/**
 * Main driving force for the Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            ui.resetString();
            Command command = parser.parse(tasks, input);
            command.execute(tasks, ui, storage);
            return ui.finalShowOnScreen();
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    String getGreeting() {
        return ui.greet();
    }
}
