package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class of bot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises a new instance of the bot.
     */
    public Duke(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        this.tasks = new TaskList();

        try {
            storage.loadData(tasks);
            ui.fileLoaded();
        } catch (DukeException e) {
            ui.showErrorMsg(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the bot until an command to terminate is issued.
     */
    public void run() {
        this.ui.sayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String nextCommand = this.ui.getNextCommand();
                Command c = Parser.parse(nextCommand);
                if (c != null) {
                    c.execute(this.ui, this.storage, this.tasks);
                    isExit = c.isExit();
                }
            } catch (DukeException e) {
                ui.showErrorMsg(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

}