package java;

import main.java.commands.Command;
import main.java.exceptions.DukeException;
import main.java.tasklist.TaskList;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.displayErrorMessage(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayErrorMessage(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("/data.txt").run();
    }

}
