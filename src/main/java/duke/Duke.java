package duke;

import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents Duke, a virtual task management system and personal assistant.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isQuitting;

    /**
     * Initializes a newly created Duke program with a saved file path.
     *
     * @param filePath path of the saved tasks file.
     */
    public Duke(String filePath) {

        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isQuitting = false;

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printWithFormatting(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program
     */
    public void run() {

        this.ui.greet();
        assert !this.isQuitting;

        while (!this.isQuitting) {
            try {
                Command c = Parser.parse(this.ui.getInput());
                c.execute(this.tasks, this.ui, this.storage);
                this.isQuitting = c.isQuitting();
            } catch (DukeException e) {
                ui.printWithFormatting(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {

        assert input != null;
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            this.ui.printWithFormatting(e.getMessage());
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
