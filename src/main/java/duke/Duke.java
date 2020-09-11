package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * Class to represent the Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke object to initialize the program.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/storage.txt");
        try {
            tasks = new TaskList(storage.loadTasksFromDisk());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseUserInput(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private void takeUserInput() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readLine();
                Command command = Parser.parseUserInput(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    private void run() {
        ui.sendMessage(ui.welcome());
        takeUserInput();
        ui.sendMessage(ui.exit());
    }

    /**
     * Runs the Duke program.
     * @param args The command line input
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
