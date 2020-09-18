package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


public class Duke {
    private static final String LOG_DIRPATH = "data";
    private static final String LOG_FILENAME = "tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isRunning = true;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(LOG_DIRPATH, LOG_FILENAME);
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadedTasks(tasks);
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public boolean getRunState() {
        return this.isRunning;
    }

    public String getResponse(String input) {
        assert !input.isEmpty() : "input cannot be empty";

        try {
            Command c = Parser.parse(input);
            this.isRunning = !c.isExit();
            return c.getResponse(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }



}
