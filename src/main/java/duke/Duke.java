package duke;

import duke.Command.Command;

import java.io.IOException;
import java.text.ParseException;

/**
 * The Duke class is the main class and drives the program
 */

public class Duke {
    private duke.Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a new driver system
     * @param filePath  the file path of the schedule list
     */
    public Duke(String filePath) throws IOException, ParseException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }


    private void run() throws IOException {
        this.ui.showWelcome();
        while (ui.sc.hasNext()) {
            ui.showLine();
            Command c = new Parser().parse(ui.sc.next());
            c.execute(this.ui, tasks, storage);
            ui.showLine();
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Duke("src/main/java/duke/resources/todo.txt").run();
    }
}
