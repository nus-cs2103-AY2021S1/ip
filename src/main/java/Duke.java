import duke.Commands.Command;
import duke.Exceptions.DukeException;
import duke.Parser.Parser;
import duke.Storage.Storage;
import duke.Tasks.*;
import duke.Ui.Ui;

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

    /**
     * Loads tasks into duke from the data file specified by the user's filePath.
     * @param filePath The file path of the data file.
     * @param folderPath The folder path of the data file.
     */
    public Duke(String filePath, String folderPath) {
        ui = new Ui();
        storage = new Storage(filePath, folderPath);
        try {
            tasks = new TaskList(storage.Load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Allow duke to start reading user input and process the commands appropriately.
     * Stops the program when the ExitCommand is encountered.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Starts up the application.
     * Allow users to set the file and folder path for the data file.
     * @param args Command line arguments for the program.
     */
    public static void main(String[] args) {
        new Duke("./src/main/java/duke/Data/data.txt", "./src/main/java/duke/Data").run();
    }
}
