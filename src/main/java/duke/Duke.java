package duke;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.Storage.StorageOperationException;
import duke.tasks.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;
import duke.commands.Command;

import java.io.InputStream;

import static duke.storage.Storage.DEFAULT_STORAGE_FILEPATH;

/**
 * Manages tasks and deadlines in a chat bot style.
 * <p>
 * Duke supports CRUD operations for 3 types of tasks:
 * <li>Todo with description</li>
 * <li>Events with duration</li>
 * <li>Deadlines</li>
 * </p>
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        this(filePath, System.in);
    }

    public Duke(String filePath, InputStream in) {
        try {
            storage = new Storage(filePath);
            taskList = storage.loadTasks();
            ui = new Ui(in);
            parser = new Parser(taskList, ui);
        } catch (StorageOperationException e ) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes Duke chat bot functionality.
     * <p>
     * Continues taking in commands until
     * "bye" command is input by user.
     * </p>
     *
     */
    public void run() {
        ui.showWelcomeMessage();
        String input = ui.getUserInput();
            while (!input.equals("bye")) {
            try {
                Command command = parser.getCommandFromInput(input);
                command.execute();
            } catch (DukeException e) {
                ui.outputBlockToUser(e.getMessage());
            }
            input = ui.getUserInput();
        }
        ui.showGoodbyeMessage();
        try {
            storage.saveTasks(taskList);
        } catch (StorageOperationException soe) {
            System.out.println(soe.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(DEFAULT_STORAGE_FILEPATH);
        duke.run();
    }
}