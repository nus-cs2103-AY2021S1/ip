package main.java.com.jacob.duke;

import main.java.com.jacob.duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructor for Duke.
     *
     * @param filePath contains file where task list is saved.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFile());
    }

    public String getResponse (String input) {
        Parser parser = new Parser();
        try {
            Command c = parser.parse(input);
            String response = c.execute(ui, tasks, storage);
            storage.writeToFile();
            assert(response != null);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
