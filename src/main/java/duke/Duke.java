package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;
import duke.inputoutput.InputOutput;
import duke.parsers.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Main class where the program is run.
 */
public class Duke {

    private InputOutput inputOutput;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        this.inputOutput = new InputOutput();
        this.storage = new Storage();
        this.taskList = storage.load();
    }

    /**
     * Main driver method (for testing purposes).
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the program until termination. Only called during testing.
     */
    public void run() {
        inputOutput.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            String input = inputOutput.readCommand();
            try {
                CommandResult result = getResult(input);
                inputOutput.show(result.getFeedbackToUser());
                storage.save(taskList);
                isExit = result.isExit();
            } catch (Exception ex) {
                inputOutput.show("\t " + ex.getMessage());
            }
        }
        System.exit(0);
    }

    /**
     * Gets the CommandResult from user input.
     *
     * @param input The user input.
     * @return The CommandResult.
     * @throws DukeException If an exception occurs.
     */
    public CommandResult getResult(String input) throws DukeException {
        Command command = Parser.parse(input);
        storage.save(taskList);
        return command.execute(taskList);
    }
}
