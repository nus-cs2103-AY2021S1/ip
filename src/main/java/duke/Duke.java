package duke;

import duke.command.Command;
import duke.exception.DukeException;


/**
 * Encapsulates the main Duke class
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;


    /**
     * Constructs a new Duke object.
     */
    public Duke() {

        storage = new Storage("duke.txt");
        try {
            tasks = storage.loadData();
        } catch (DukeException e) {

            tasks = new TaskList();
        }

    }


    /**
     * Gets response based on input
     * @param input
     * @return the string containing the response
     */
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            assert(c != null);
            assert(tasks != null);
            assert(storage != null);
            output = c.execute(tasks, storage);
        } catch (DukeException e) {
            output = e.toString();
        }

        return output;
    }




}
