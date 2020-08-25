import Command.Command;
import Exceptions.DukeException;
import ParserStorageUi.Parser;
import ParserStorageUi.Storage;
import ParserStorageUi.Ui;
import Task.TaskList;

public class Duke {

    /** The storage assigned to Duke **/
    private Storage storage;

    /** The TaskList assigned to Duke **/
    private TaskList tasks;

    /** The Ui interaction handler **/
    private Ui ui;

    /**
     * Initializes Duke
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /** Run the whole program **/
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

    /** The main program of all file **/
    public static void main(String[] args) {
        new Duke("data").run();
    }
}