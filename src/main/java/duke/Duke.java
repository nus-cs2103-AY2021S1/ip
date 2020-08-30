package duke;

import java.nio.file.Path;
import duke.command.Command;

/**
 * The Duke program chat bot which can help users keep track of tasks.
 */
public class Duke {

    /** The path where the tasks are saved. */
    public static final Path path = java.nio.file.Paths.get(".", "data.txt");

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /** Create and initialize Duke. */
    public Duke() {
        ui = new Ui();
        storage = new Storage(path);
        taskList = new TaskList(storage.load());
    }


    /** Start the chat with user. */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readInput();
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
        new Duke().run();
    }

    public String getResponse(String input) {
        return input;
    }
}
