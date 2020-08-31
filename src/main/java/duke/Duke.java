package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.util.List;

public class Duke {

    public static final String FILE_DIRECTORY = "data/";
    public static final String FILE_NAME = "duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(FILE_DIRECTORY, FILE_NAME);
            List<Task> taskList = storage.generateTaskList();
            tasks = new TaskList(storage.generateTaskList());
        } catch (IOException | DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (!fullCommand.isEmpty()) {
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                }
            } catch (DukeException | IOException e) {
                ui.showError(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
