package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskException;
import duke.task.TaskList;
import duke.ui.UI;
import duke.ui.UiException;

/**
 * Handles the logic for the application.
 */
public class Duke {
    private static final String FILE_PATH = "./data/taskList.txt";

    private TaskList taskList;
    private Storage storage;
    private final UI ui;

    /**
     * Creates an instance of Duke.
     * The filepath defaults to "./data/taskList.txt".
     */
    public Duke() {
        ui = new UI();
        taskList = new TaskList();
        try {
            storage = new Storage(FILE_PATH);
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
            } catch (UiException e) {
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
        new Duke().run();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
