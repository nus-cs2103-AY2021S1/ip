package duke;
import duke.command.Command;

public class Duke {

    /** File path for storage object to write to. */
    private Storage storage;
    private TaskList taskItems;
    private Ui ui;
    public static final String FILE_PATH = "data/duke.txt";

    /**
     * Instantiates Duke Object which initializes variables needed throughout program. 
     * 
     * @param filePath path for storage object to write to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskItems = new TaskList(storage.loadTasksFromMemory());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskItems = new TaskList();
        }
    }

    /**
     * Greets the user upon execution of duke process.
     * Reads User input and execute appropriate command.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskItems, ui, storage);
                isExit = c.isExit();
            } catch (DukeException duked) {
                ui.showError(duked.getMessage());
            } finally {
            }
        }
    }
    /**
     * Executes the Duke process.
     * 
     * @param args command line argument it is a collection of variables in the string format.
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run(); 
    }
}
