package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskException;
import duke.task.TaskList;
import duke.ui.UI;
import duke.ui.UIException;

/**
 * Consolidates the various elements.
 * Drives the application.
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private final UI ui;

    /**
     * Creates an instance of Duke.
     *
     * @param filepath Valid path relative to current root directory
     */
    public Duke(String filepath) {
        ui = new UI();
        taskList = new TaskList();
        try {
            storage = new Storage(filepath);
            storage.load(taskList);
        } catch (StorageException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Accepts user inputs.
     * Processes inputs.
     */
    private void run() {
        ui.showHelloMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                String result = command.execute(taskList, storage);
                ui.printResult(result);
                ui.showLine();
                isExit = command.isExit();
            } catch (UIException e) {
                ui.showLine();
                ui.showErrorMessage(e.getMessage());
                ui.showLine();
            } catch (DukeUnknownCommandException | StorageException | TaskException e) {
                ui.showErrorMessage(e.getMessage());
                ui.showLine();
            }
        }

        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Duke("./data/taskList.txt").run();
    }
}
