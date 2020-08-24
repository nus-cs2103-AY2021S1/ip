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
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
        try {
            this.tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command command = this.parser.findCommand(fullCommand);
                command.execute(this.tasks, this.ui, this.storage);
                this.storage.save(tasks.getList());
                isExit = command.isExit();
            } catch (DukeException | IOException e) {
                this.ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}


