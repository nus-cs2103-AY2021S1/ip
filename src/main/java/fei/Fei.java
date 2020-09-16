package fei;

import fei.command.Command;
import fei.exception.FeiException;
import fei.tool.Parser;
import fei.tool.Storage;
import fei.tool.TaskList;
import fei.tool.Ui;

public class Fei {

    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private boolean isExit;

    /**
     * Create a Fei's chatterbox with a file path you want to store all your data in.
     *
     * @param filePath a String on file path in format: "./data/*.txt".
     */
    public Fei(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        assert isExit == false: isExit = false;
        try {
            tasks = storage.load();
        } catch (FeiException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            this.isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (FeiException e) {
            return e.getMessage();
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

}
