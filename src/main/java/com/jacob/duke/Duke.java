package main.java.com.jacob.duke;

import main.java.com.jacob.duke.command.Command;
import main.java.com.jacob.duke.io.Parser;
import main.java.com.jacob.duke.io.Storage;

public class Duke {
    private Storage storage;
    private DukeList dukeList;
    private Ui ui;
    /**
     * Constructor for Duke.
     *
     * @param filePath contains file where task list is saved.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        dukeList = storage.readFile();
    }

    public String getResponse (String input) {
        Parser parser = new Parser();
        try {
            Command c = parser.parse(input);
            String response = c.execute(ui, dukeList, storage);
            storage.writeToFile();
            assert(response != null);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
