package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Initialises a functional chat-bot to save and display various tasks added by the user.
 * Includes tasks like toDo, Deadline and Events
 */
public class Duke {

    /** Storage object to save tasks to data file. */
    private Storage storage;

    /** TaskList to hold user tasks. */
    private TaskList tasks;

    /** Ui object to print user messages. */
    private Ui ui;

    public Duke() {

    }

    /**
     * Loads tasks into duke from the data file specified by the user's filePath.
     * @param filePath The file path of the data file.
     * @param folderPath The folder path of the data file.
     */
    public Duke(String filePath, String folderPath) {
        ui = new Ui();
        storage = new Storage(filePath, folderPath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }


    /**
     * Allow duke to start reading user input and process the commands appropriately.
     * Stops the program when the ExitCommand is encountered.
     */
    public String run(String command) {

        String response = ui.showLine(); // show the divider line ("_______")

        try {
            String fullCommand = command;
            Command c = Parser.parse(fullCommand);
            response = response + c.execute(tasks, ui, storage);
            //isExit = c.isExit();
        } catch (DukeException e) {
            response = response + "\n" + ui.showError(e.getMessage());
        } finally {
            response = response + "\n" + ui.showLine();
        }
        return response;
    }

    public String welcome() {
        return ui.showWelcome();
    }

    /**
     * Starts up the application.
     * Allow users to set the file and folder path for the data file.
     * @param args Command line arguments for the program.

    public static void main(String[] args) {
        new Duke("./src/main/java/duke/Data/data.txt", "./src/main/java/duke/Data").run();
    }
    */

}
