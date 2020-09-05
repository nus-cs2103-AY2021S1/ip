package duke;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.logic.CommandParser;
import duke.logic.commands.Command;
import duke.model.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {
    private static final String FILE_PATH = "data/DukeDB.txt";
    private Storage storage;
    private TaskManager tm;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tm = new TaskManager((storage.load()));
        } catch (Exception e) {
            ui.showLoadingError();
            tm = new TaskManager();
        }
    }

    public void run() {
        ui.showWelcome();;
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = CommandParser.parse(fullCommand);
                c.execute(tm, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
