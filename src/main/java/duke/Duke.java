package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Engages all the other classes to execute Duke
 */
public class Duke {
    public static String filePath;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        this.filePath = filePath;
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Loops to continuously check for user input
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.printError((DukeException) e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
