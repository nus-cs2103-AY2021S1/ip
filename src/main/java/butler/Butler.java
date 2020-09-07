package butler;

import butler.command.Command;
import butler.exception.ButlerException;
import butler.io.Parser;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

/**
 * Represents a butler that manages a list of tasks for the user.
 *
 * Butler maintains a list of tasks across different sessions.
 * Data of the list of tasks are saved in hard disk within data/tasks.txt
 * relative to the program file location.
 */
public class Butler {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Butler(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ButlerException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Butler("data/tasks.txt").run();
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ButlerException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
