package duke;

import java.io.FileNotFoundException;
import java.nio.channels.FileLockInterruptionException;

import duke.exception.DukeException;

/**
 * The Duke class is the main class in which the program is run.
 *
 */
public class Duke {
    private final static String FILE_PATH = System.getProperty("user.dir") + "\\data\\duke.txt";
    private final static String DATA_PATH = System.getProperty("user.dir") + "\\data";

    private Storage storage = new Storage(FILE_PATH, DATA_PATH);
    private Ui ui = new Ui();
    private TaskList taskList;
    private Parser parser = new Parser();

    /**
     * Initializes a Duke object
     */
    public Duke() {
        System.out.println(FILE_PATH);
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
