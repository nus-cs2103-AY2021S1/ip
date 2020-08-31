package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Represents the chat bot Duke.
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Represents the chat bot duke.
     */
    public Duke() {
        storage = Storage.dbInstance();
        taskList = storage.getTaskListFromDatabase();
        ui = new Ui();
    }

    /**
     * Starts up the chat bot by welcoming and listing out saved tasks. Prompts the user to enter
     * valid commands until the bye command is issued.
     */
    public void start() {
        ui.printWelcomeMessage();
        ui.printDatabaseTasks(taskList);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.getExitStatus();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
