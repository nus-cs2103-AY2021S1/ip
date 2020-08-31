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

    private boolean hasExited = false;
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
     * Accepts and processes user inputs.
     * This supports the CLI for Duke.
     */
    private void run() {
        ui.showHelloMessage();
        while (!hasExited) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                String result = command.execute(taskList, storage);
                ui.printResult(result);
                ui.showLine();
                hasExited = command.isExit();
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

    /**
     * Processes user inputs.
     * This supports the GUI for Duke.
     *
     * @param input User input text.
     * @return Duke logic response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String result = command.execute(taskList, storage);
            hasExited = command.isExit();
            return result;
        } catch (StorageException | TaskException | DukeUnknownCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns if the program should exit.
     *
     * @return True if an exit command had been received.
     */
    public boolean hasExited() {
        return hasExited;
    }

    /**
     * Runs the Duke CLI program.
     *
     * @param args Execution parameters.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
