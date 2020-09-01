package duke;

import java.io.FileNotFoundException;

import duke.exception.DukeException;

/**
 * The Duke class is the main class in which the program is run.
 *
 */
public class Duke {
    private Storage storage = new Storage("./src/main/java/data/duke.txt");
    private Ui ui = new Ui();
    private TaskList taskList;
    private Parser parser = new Parser();
    /**
     * Initializes a Duke object
     */
    public Duke() {
        try {
            taskList = new TaskList(storage);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (FileNotFoundException err) {
            System.out.println("File not found in filepath provided");
        }
    }

    public String getResponse(String input) throws DukeException {
        Command nextCommand = parser.interpret(input);
        return nextCommand.execute(taskList, ui);
    }
}
