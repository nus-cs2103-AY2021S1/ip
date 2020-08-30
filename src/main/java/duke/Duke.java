package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Cli;
import duke.ui.Gui;
import duke.ui.Ui;
import javafx.application.Application;

/**
 * Runs the application.
 */
public class Duke {
    private final Parser parser;
    private final Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for the Duke object.
     */
    public Duke(Ui ui) {
        this.ui = ui;
        try {
            this.storage = new Storage("duke.json");
            this.taskList = this.storage.load();
        } catch (DukeException e) {
            ui.systemMessage(e.getMessage());
            this.taskList = new TaskList();
        } finally {
            this.parser = new Parser(this.taskList, this.ui);
            ui.start();
        }
    }

    /**
     * TODO
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
