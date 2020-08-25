package duke;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Main class of Duke.
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Construct the Duke initialized with the path of the target file to store and read.
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.msg);
            tasks = new TaskList();
        }
    }

    /**
     * Run the Duke.
     */
    public void run() {
        ui.showWelcome();
        String fullCommand = ui.readCommand();

        while(true) {
            try {
                String[] commands = Parser.parse(fullCommand);
                if(commands[0].equals("bye")) break;
                tasks.runCommand(commands, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.msg);
            }
            fullCommand = ui.readCommand();
        }
        ui.bye();
    }

    /**
     * Execute the program.
     * @param args the argument Array of String.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
