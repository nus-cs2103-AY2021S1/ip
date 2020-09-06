package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Runs the application.
 */
public class Duke {
    private final Parser parser;
    private final Ui ui;
    private final Context context;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for the Duke object.
     */
    public Duke(Ui ui) {
        assert ui != null : "ui should never be null";
        this.ui = ui;
        try {
            this.storage = new Storage("duke.json");
            this.taskList = this.storage.load();
        } catch (DukeException e) {
            ui.systemMessage(e.getMessage());
            this.taskList = new TaskList();
        } finally {
            this.context = new Context(this.taskList, this.ui);
            this.parser = new Parser(this.context);
            ui.start();
        }
    }

    /**
     * Calls the next iteration of Duke. Uses Ui#nextLine as input
     * for the next command, runs that command and sends the output
     * to Ui#systemMessage. Any errors will also be sent to
     * Ui#systemMessage.
     */
    public void nextIteration() {
        String input = ui.nextLine();
        try {
            parser.parseAndRun(input);
            this.storage.save(this.taskList);
        } catch (DukeException e) {
            ui.systemMessage(e.getMessage());
        }
    }

}
