package duke.main;

import duke.command.Command;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.parser.Parser;


import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.loadFileError();
            storage.createFile();
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.loadDateError();
            tasks = new TaskList();
        }
    }

    public void run() {
        // display starting UI
        ui.showStartMessage();
        ui.showCurrentTasks(tasks.getTaskList());

        Parser parser = new Parser(ui);
        boolean isDone = false;
        while (!isDone) {
            try {
                String command = ui.readCommand();
                Command c = parser.parse(command);
                c.execute(tasks, ui, storage);
                isDone = c.isDone();
            } catch (DukeException ex) {
                ui.printExceptions(ex);
            }

        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
