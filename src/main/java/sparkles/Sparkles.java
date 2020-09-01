package sparkles;

import sparkles.command.Command;
import sparkles.task.TaskList;
import sparkles.util.Parser;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represents a Sparkles object, a chat bot that respond to user's inputs.
 */
public class Sparkles {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates the Sparkles object with the designated filepath.
     * Initialised the Ui, Storage and TaskList object.
     *
     * @param filePath file path where task.txt is to be created
     */
    public Sparkles(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SparklesException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Sparkles() {
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SparklesException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Sparkles("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        return input;
    }
}
