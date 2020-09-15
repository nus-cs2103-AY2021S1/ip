package duke;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

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

    /**
     * Returns a response based on user input.
     *
     * @param input The user's line of input.
     * @return Returns Duke's response.
     */
    public String getResponse(String input) {
        try {
            if (tasks == null) {
                Storage storage = new Storage("data/tasks.txt");
                try {
                    tasks = storage.load();
                    ui = new Ui();
                } catch (DukeException e) {
                    ui.showLoadingError(e);
                }
            }
            String response = Parser.parse(input, tasks);
            if (response.equals(ui.goodbye())) {
                exit();
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Exits the application.
     */
    public void exit() {
        new Timer().schedule(new TimerTask() {
            public void run () {
                Platform.exit();
                System.exit(0); }
        }, 2000);
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
