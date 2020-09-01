package duke;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The main class for Project Duke.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Project Duke.
     * @param filePath The file path where the user's task list would be stored.
     */
    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        try {
            tasks = storage.load();
            ui = new Ui();
        } catch (DukeException e) {
            assert false;
            ui.showLoadingError(e);
        }
    }

    public Duke() {}

    public String getResponse(String input) {
        try {
            if (tasks == null) {
                Storage storage = new Storage("data/tasks.txt");
                try {
                    tasks = storage.load();
                    ui = new Ui();
                } catch (DukeException e) {
                    assert false;
                    ui.showLoadingError(e);
                }
            }
            String response = Parser.parse(input, tasks, true);
            if (response.equals(ui.goodbye(true))) {
                new Timer().schedule(new TimerTask() {
                    public void run () { Platform.exit(); }
                }, 2000);
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Initializes the Duke User Interface with the user's previously saved tasks (if any)
     */
    public void run() {
        ui.initializeDukeUI(tasks);
    }

    /**
     * The main method for Project Duke.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
