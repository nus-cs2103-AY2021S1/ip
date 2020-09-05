package butler;

import butler.command.Command;
import butler.exception.ButlerException;
import butler.io.Parser;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

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

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ButlerException e) { // reaches here due to invalid command
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Butler("data/tasks.txt").run();
    }
}
