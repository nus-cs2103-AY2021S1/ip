package clippy;

import clippy.command.Command;

import clippy.exception.ClippyException;

import clippy.parser.Parser;

import clippy.storage.Storage;

import clippy.task.Task;
import clippy.task.TaskList;

import clippy.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a task-tracking chat bot called Clippy with a Command Line Interface.
 */
public class Clippy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Clippy object with a given file path to where the save file is expected to be.
     * Attempts to load saved tasks from the save file.
     * 
     * @param filePath expected relative path of save file for saved tasks
     */
    public Clippy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        
        try {
            ArrayList<Task> loadedArrayListOfTask = storage.load();
            tasks = new TaskList(loadedArrayListOfTask);
            tasks.updateAllTaskIndices();
        } catch (ClippyException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Constructs Clippy object with a pre-defined save file path (./data/savefile.txt).
     * Attempts to load saved tasks from the pre-defined save file.
     */
    public Clippy() {
        String preDefinedFilePath = "./data/savefile.txt";
        
        ui = new Ui();
        storage = new Storage(preDefinedFilePath);
        
        try {
            ArrayList<Task> loadedArrayListOfTask = storage.load();
            tasks = new TaskList(loadedArrayListOfTask);
            tasks.updateAllTaskIndices();
        } catch (ClippyException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Returns the response of Clippy to a user input.
     * 
     * @param input User input.
     * @return Response of Clippy.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (ClippyException e) {
            return e.getMessage();
        }
    }
}
