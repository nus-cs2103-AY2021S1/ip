package duke;

import java.nio.file.Path;
import duke.command.*;

/**
 * The Duke program chat bot which can help users keep track of tasks.
 *
 * @author  Yen Pin Hsuan
 * @version 1.0
 */
public class Duke {

    /** The path where the tasks are saved. */
    public static final Path path = java.nio.file.Paths.get(".", "data.txt");

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Create and initialize Duke.
     * @param filePath The path where tasks are stored.
     */
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }


    /** Start the chat with user. */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(taskList, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main method for Duke.
     * Creates a Duke and start interacting with user.
     */
    public static void main(String[] args) {
        new Duke(path).run();
    }
}
