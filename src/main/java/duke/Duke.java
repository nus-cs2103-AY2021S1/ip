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

    /**
     * Returns Introduction of duke based on whether duke met user already.
     * @return Introduction string of duke.
     */
    public String intro() {
        String intro = "Hi again Poppins! Thanks for talking to me again :)\n"
            + "What adventures do you want me to record today?\n"
            + "Once again if you forgot how to communicate with me type 'help' :D";
        if (storage.folderIsCreated()) {
            intro = "Hi Mary Poppins! My name is Duck. I am not familiar with the human language.\n"
                + "But I would very much like to know about your adventures :(\n"
                + "Would you type 'help' to know about all the commands that I understand? :D";
        }
        return intro;
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
