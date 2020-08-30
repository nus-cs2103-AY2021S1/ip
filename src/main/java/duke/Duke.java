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
    public boolean isExit;

    /**
     * Constructs the Duke initialized with the path of the target file to store and read.
     *
     * @param filePath the path of the target file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMsg());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke.
     */
    public void run() {
        this.isExit = false;
        ui.showWelcome();
        String fullCommand = ui.readCommand();

        while (!this.isExit) {
            try {
                String[] commands = Parser.parse(fullCommand);
                if (commands[0].equals("bye")) {
                    this.isExit = true;
                    break;
                }
                tasks.runCommand(commands, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMsg());
            }
            fullCommand = ui.readCommand();
        }
        ui.bye();
    }

    /**
     * Execute the program.
     *
     * @param args the argument Array of String.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public String getResponse(String input) {
        try {
            String[] commands = Parser.parse(input);
            if (commands[0].equals("bye")) {
                isExit = true;
                ui.bye();
                return "[DUKE]\n" + ui.showingString;
            }
            tasks.runCommand(commands, ui, storage);
            return "[DUKE]\n" + ui.showingString;
        } catch (DukeException e) {
            ui.showError(e.getMsg());
            return "[DUKE]\n" + ui.showingString;
        }
    }

    public String showWelcomeToGui() {
        ui.showWelcome();
        return "[DUKE]\n" + ui.showingString;
    }
}
