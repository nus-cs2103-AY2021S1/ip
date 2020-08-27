package duke;

import duke.command.Command;
import duke.io.Storage;
import duke.io.TaskList;
import duke.parser.Parser;
import java.io.File;

/**
 * This is the start of the Duke application. There 3 main task that the application can record,
 * ToDo, Event and Deadline.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class Duke {

    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Class constructor that initialised the application.
     * Ui, Storage and TaskList are initialised in this constructor.
     *
     * @param filePath path of duke.txt, duke.txt acts as a simple database.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Main logic of application execution.
     * Use Duke class initialised class for application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main class. Entry point for Duke application.
     *
     * @param args stores hava command line arguments.
     */
    public static void main(String[] args) throws Exception {
        final String dataDir = CURRENT_DIRECTORY + File.separator + "data";
        final String dataFile = "duke.txt";
        new Duke(dataDir + File.separator + dataFile).run();
    }
}
