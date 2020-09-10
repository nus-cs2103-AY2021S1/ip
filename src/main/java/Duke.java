import java.time.format.DateTimeParseException;

/**
 * Represents a task-tracking chat bot with Command Line Interface.
 */
public class Duke {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Duke object which represents a chat bot and loads saved tasks if save file exists.
     * @param filePath relative path of save file for saved tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Constructs Duke object and loads saved tasks (from a pre-defined path) if save file exists.
     */
    public Duke() {
        String filePath = "./data/savefile.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }
    
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Incorrect date format. Retry with YYYY-MM-DD.";
        }
    }
    
}
