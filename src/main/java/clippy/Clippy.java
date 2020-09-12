package clippy;

import clippy.command.Command;
import clippy.exception.ClippyException;
import clippy.parser.Parser;
import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Represents a task-tracking chat bot with Clippy.Command Line Interface.
 */
public class Clippy {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Clippy.Clippy object which represents a chat bot and loads saved tasks if save file exists.
     * @param filePath relative path of save file for saved tasks as specified by user
     */
    public Clippy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            tasks.updateAllTaskIndices();
        } catch (ClippyException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Constructs Clippy.Clippy object and loads saved tasks (from a pre-defined default path) if save file exists.
     */
    public Clippy() {
        String filePath = "./data/savefile.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            tasks.updateAllTaskIndices();
        } catch (ClippyException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }
    
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (ClippyException e) {
            return e.getMessage();
        }
    }
    
}
