package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.stage.Stage;

/**
 * The class Duke denotes the faithful robot.
 *
 * @author Alvin Chee
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Stage stage;

    /**
     * Constructs a Duke robot.
     *
     * @param filePath  FilePath to store the data file.
     */
    Duke(String filePath) {
        storage = new Storage(filePath);
        storage.addDirectoryIfRequired();
        tasks = new TaskList(storage.load());
    }

    public String intro() {
        return "Hi Mary Poppins! My name is Duck. I am not familiar with the human language.\n"
            + "But I would very much like to know about your adventures :(\n"
            + "Would you type 'help' to know about all the commands that I understand? :D";
    }

    /**
     * Gets the stage from Main.
     *
     * @param stage Stage to be showcased.
     */
    public void getStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Returns the stage stored.
     */
    public Stage returnStage() {
        return stage;
    }

    /**
     * Executes all the operations stated.
     *
     * @param input  String arrays of operations.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(tasks, storage);
    }
}
