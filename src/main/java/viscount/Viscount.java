package viscount;

import viscount.command.Command;
import viscount.exception.ViscountException;

import java.io.IOException;

/**
 * Represents Viscount, a chatbot that helps the user keep track of tasks.
 */
public class Viscount {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Viscount(String filePathString) throws IOException {
        this.storage = new Storage(filePathString);
        this.ui = new Ui();
        this.tasks = new TaskList(storage.loadFromDisk());
    }
    
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.executeAndGetResponse(tasks, ui, storage);
        } catch (ViscountException e) {
            return e.getMessage();
        }
    }
    
    public Ui getUi() {
        return ui;
    }
}
