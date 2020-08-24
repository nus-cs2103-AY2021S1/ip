package duke.main;

import duke.command.Command;
import duke.error.DukeError;

/**
 * The main class for running Duke, the ChatBot.
 */

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final UI ui;

    /**
     * Initialisation for Duke, the ChatBot.
     * @param filepath Requires a filepath which will be relative to where Duke was ran, to save and load files from
     */
    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        tasks = storage.loadData(ui);
    }

    /**
     * Runs Duke, the ChatBot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeError e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
