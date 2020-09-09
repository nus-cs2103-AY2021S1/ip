package duke;

import java.util.Scanner;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Starts Duke which a user can give
 * text commands to.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a Duke object
     *
     * @param filePath  Location of file where data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage(filePath);
        taskList = TaskList.generateTaskList(storage);
    }

    /**
     * Starts the Duke application.
     *
     * @param args Array of command-line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/tasks.txt");
        duke.start();
        duke.runUntilExitCommand();
        duke.exit();

    }

    private void start() {
        ui.start(taskList);
    }

    private void runUntilExitCommand() {
        boolean isExitCommand = false;
        String input = ui.readCommand();
        while (!isExitCommand) {
            Command command;
            try {
                ui.printDivider();
                command = Parser.parse(input);
                command.execute(ui, taskList);
                isExitCommand = command.isExitCommand();
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            }
            ui.printAdditionActionMessage();
            input = ui.readCommand();
        }
    }

    private void exit() {
        ui.printGoodbyeMessage();
    }
}
