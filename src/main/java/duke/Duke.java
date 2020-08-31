package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The main class for the project. Initialises the Duke class and runs it.
 */
public class Duke {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Instantiates a Duke object.
     */
    Duke() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("data/data.txt");
        try {
            tasks = new TaskList(storage.processStorage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the Duke programme.
     */
    void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayMessage(e.getMessage());
            } finally {
                ui.showLine();
                ui.newLine();
            }
        }
    }

    /**
     * The main method for the project.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
