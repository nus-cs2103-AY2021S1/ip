package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Dook the all purpose chatbot to serve your human needs. Made from the finest IDEs, Dook will be the most
 * efficient task list you have ever laid your hands upon.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    /**
     * To start Dook chatbot.
     */
    public void run() {

        // Initialise
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.print(e.toString());
            }
        }
        ui.showExit();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
