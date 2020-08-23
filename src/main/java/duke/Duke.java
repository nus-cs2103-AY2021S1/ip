package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private static final Path filePath = Paths.get(".", "data", "duke.txt");
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    Duke() {
        this.ui = new Ui();
        this.storage = new Storage(Duke.filePath);

        try {
            this.tasks = new TaskList(this.storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        this.ui.print("Hello! I'm Duke\nWhat can I do for you?");

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

    }
}
