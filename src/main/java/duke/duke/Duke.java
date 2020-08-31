package duke.duke;

import duke.command.DukeException;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

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
     * Begin the Duke chat bot program.
     */
    public void run() {
        ui.showWelcome();
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

    public static void main(String[] args) {
        new Duke().run();
    }
}


