package viscount;

import java.io.IOException;

import viscount.command.Command;
import viscount.exception.ViscountException;


/**
 * Represents Viscount, a chatbot that helps the user keep track of tasks.
 */
public class Viscount {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates a new Viscount chatbot.
     *
     * @param filePathString String of path of data file loaded.
     * @throws IOException if an error occurs while loading data from save file.
     */
    public Viscount(String filePathString) throws IOException {
        this.storage = new Storage(filePathString);
        this.ui = new Ui();
        this.tasks = new TaskList(storage.loadFromDisk());
    }

    /**
     * Gets the appropriate string response from Viscount, given an input.
     *
     * @param input Input to be parsed and responded to.
     * @return The appropriate string response from Viscount.
     */
    public String getResponse(String input) throws ViscountException {
        Command command = Parser.parse(input);
        return command.executeAndGetResponse(tasks, ui, storage);
    }

    /**
     * Gets the UI of Viscount.
     *
     * @return UI of Viscount.
     */
    public Ui getUi() {
        return ui;
    }
}
