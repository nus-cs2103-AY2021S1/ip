package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser parser = new Parser(fullCommand);
                Command c = parser.parse();
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine(); // show the divider line ("_______")
            }
        }
    }

    public void testRun(String[] input) {
        ui.showWelcome();
        for (String line : input) {
            try {
                Parser parser = new Parser(line);
                Command c = parser.parse();
                c.execute(tasks, ui, storage);
                if (c.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine(); // show the divider line ("_______")
            }
        }
    }


    public static void main(String[] args) {
         new Duke("data/tasks.txt").run();
    }
}
