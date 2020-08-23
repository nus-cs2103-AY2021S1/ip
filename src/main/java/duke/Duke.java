package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a Task Chatbot that helps the user to track of the task to be done.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     * Loads and reads the data from the text file
     *
     * @param filePath the path of the text file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Processes the user input and execute the commands
     */
    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * main method that runs the run method
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
