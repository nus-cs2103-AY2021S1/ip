package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a Task Chatbot that helps the user to track of the task to be done.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     * Loads and reads the data from the text file
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Processes the user input and execute the commands
     * @return String that should be outputted from the command.
     */
    public String run(String input) {
        assert !input.equals("");
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getResponse(String input) {
        return new Duke().run(input);
    }
}
