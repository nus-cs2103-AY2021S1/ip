package duke;

import duke.command.Command;
import duke.command.CommandResponse;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The Duke programme implements an application that allow users
 * to track 3 different types of tasks, namely Todo, Deadline
 * and Event to a list.
 *
 * @author Ngoh Wei Yue
 * @version 0.1
 * @since 14-08-2020
 */
public class Duke {

    /** Storage to store existing tasks in hard disk */
    private final Storage storage;

    /** TaskList to store tasks in a data structure */
    private final TaskList tasks;

    /** Ui to interact with the users */
    private Ui ui;
    /**
     * Constructs a <code>Duke</code> object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    /**
     * Get response from Duke.
     *
     * @param input Input from user.
     * @return Response base on the Command executed.
     */
    public CommandResponse getResponse(String input) {
        try {
            Command c = Parser.parse(input, tasks);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return new CommandResponse(e.getMessage(), false);
        }
    }

    /**
     * Runs and terminates the application when user calls for it.
     *
     * @return Nothing.
     */
    private void run() {
        ui.sendGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showLine();

                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, tasks);
                CommandResponse response = c.execute(tasks, ui, storage);

                isExit = response.getShouldExit();
                ui.printMessage(response.getResponseMessage());
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        System.exit(0);
    }

    /**
     * Runs the application. This is the main method which
     * serves as the entry point of the Duke application.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
