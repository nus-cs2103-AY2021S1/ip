package raythx98.duke;

import java.util.Scanner;

import raythx98.duke.command.Command;
import raythx98.duke.exception.DukeException;
import raythx98.duke.parser.Parser;
import raythx98.duke.storage.Storage;
import raythx98.duke.task.TaskList;
import raythx98.duke.ui.Ui;

/**
 * Main driving force for the Duke application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showLine();
                String nextInputLine = ui.readCommand();
                Command command = parser.parse(tasks, nextInputLine);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
