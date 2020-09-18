package duke;

import duke.command.Command;
import duke.command.CommandResponse;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The Duke class is the main class where the chat bot runs.
 */
public class Duke {

    /** Storage to store existing Task in hard disk */
    private Storage storage;

    /** TaskList to store Task in a data structure */
    private TaskList tasks;

    /** Ui to interact with the user */
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
     * Gets response from Duke after executing a Command.
     *
     * @param input Input from user.
     * @return Response based on the Command executed.
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
     * Only use for testing.
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
     * Runs the application. This is the main method for
     * testing purposes.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
