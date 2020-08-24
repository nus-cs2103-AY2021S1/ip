package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Encapsulates the main Duke class
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke instance given a filepath
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadData();
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main logic
     */
    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getLine();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs the program
     * @param args
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
