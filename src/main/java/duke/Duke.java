package duke;

import duke.commands.Command;
import duke.tasks.TaskList;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.findCommand(fullCommand);
                command.execute(tasks, ui, storage);
                storage.save(tasks.getList());
                isExit = command.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}


