
package duke;

import duke.command.Command;
/**
 * Responsible for interpreting the input and interacting with the User.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private final Parser parser;


    /**
     * Initialised duke.Duke with a designated location to read and save the data.
     * @param filePath File location to read and save data.
     */
    public Duke(String filePath) {
        this.parser = new Parser();
        try {
            this.storage = new Storage(filePath);
        } catch (DukeException e) {
            this.storage = new Storage();
        }

        try {
            this.tasks = new TaskList(this.storage.loadDefaultFile());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    public String getWelcome() {
        return Ui.showWelcome();
    }

    /**
     ** Take in user input and carry out the respective command based on the input command.
     * @param input The user input.
     * @return Either Success Message or Error Message due to bad formatting.
     */
    public String processInput(String input, Runnable terminationFunction) throws DukeException {
        Command command = parser.createCommandFromInput(input);
        assert command != null : "Error in Parser";
        return command.execute(storage, tasks, terminationFunction);
    }
}
