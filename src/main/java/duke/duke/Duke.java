package duke.duke;

import duke.command.DukeException;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.fxml.FXML;

/**
 * Duke class that runs the Duke chat bot program.
 */
public class Duke {

    private Storage storage;
    private TaskList list;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke object.
     */
    public Duke() {
        this.storage = new Storage();
        this.list = storage.getList();
        this.ui = new Ui();
        this.parser = new Parser(this.list);
    }

    /**
     * Begin the Duke chat bot program. Deprecated since v0.2.
     */
    public void run() {
        ui.showOutput(ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                ui.showOutput(parser.processCommand(fullCommand));
                isExit = parser.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.updateFile(list);
    }

    /**
     * Gets the response after processing the user input.
     *
     * @param input The input provided by the user.
     * @return String representation of the resulting command that was executed.
     */
    @FXML
    public String getResponse(String input) {
        try {
            return parser.processCommand(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}


