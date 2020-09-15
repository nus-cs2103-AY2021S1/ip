package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Represents the main class for the Duke application
 */

public class Duke {
    public static final String FILENAME = System.getProperty("user.dir") + "/data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke object with the specified file path.
     *
     * @param filePath Path of the file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            if (c instanceof ExitCommand) {
                Platform.exit();
            } else {
                response = c.executeToString(tasks, ui, storage);
            }

        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

}



