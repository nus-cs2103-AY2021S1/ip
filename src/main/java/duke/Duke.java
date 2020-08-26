package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.tool.Parser;
import duke.tool.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents the duke system.
 */
public class Duke {

    /** Storage of the system */
    private final Storage storage;

    /** Task list that stores tasks */
    private TaskList tasks;

    /** User interface to interact with user */
    private final Ui ui;

    /**
     * Creates new Duke chat bot from given storage path.
     *
     * @param filePath Data file path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printLog(e.getMessage());
        }
    }

    /**
     * Runs the Duke system.
     */
    public void run() {

        //Start greet
        ui.showLogo();
        ui.showWelcomeMessage();
        boolean isExit = false;

        //Run until see the exit command
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.excute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printLog(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
