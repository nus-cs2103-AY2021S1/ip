package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.ReadFailedException;
import duke.task.Tasks;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private Tasks tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = initialiseStorage(filePath);
            this.tasks = storage.getTasks();
        } catch (ReadFailedException ex) {
            this.ui.printDukeException(ex);
            this.tasks = new Tasks();
        }
    }

    private static Storage initialiseStorage(String filePath) throws ReadFailedException {
        Storage storage;
        try {
            storage = new Storage(filePath);
        } catch (IOException ex) {
            throw new ReadFailedException("data");
        }
        return storage;
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException ex) {
                this.ui.printDukeException(ex);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
