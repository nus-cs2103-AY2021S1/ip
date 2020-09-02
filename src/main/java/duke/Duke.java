package duke;

import duke.ui.Ui;
import duke.command.Command;

/**
 * Represents a Duke executable program.
 * @author Tee Kok Siang
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.load());
    }


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getLocalizedMessage();
        }
    }
}
