package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import duke.task.TaskList;
import duke.command.Command;

public class Duke {
    private static final Path filePath = Paths.get(".", "data", "duke.txt");

    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

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

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
