package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class, responsible for running the program.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean isRunning;

    /**
     * Creates a <code>Duke</code> object.
     * @param filePath The path of the data file that this object interacts with
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isRunning = true;
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showFileLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String fullCommand) {
        String[] splitted = fullCommand.split("\\s+");
        boolean isExiting = false;
        if (splitted.length == 1 && splitted[0].equals("hello")) {
            isRunning = true;
            return ui.showWelcome();
        } else if (splitted.length == 1 && splitted[0].equals(ExitCommand.COMMAND)) {
            isExiting = true;
        }
        if (isRunning) {
            if (isExiting) {
                isRunning = false;
            }

            try {
                Command c = Parser.parse(fullCommand);
                return c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }
        }
        return "I'm sleeping...zzz";
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.requestCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                storage.write(tasks); // temporary until having better implementation
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
