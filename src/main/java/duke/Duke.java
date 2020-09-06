package duke;

import javafx.scene.control.Label;

/**
 * Main class for running bot.
 */
public class Duke {
    private static Label outputLabel;
    private static final String FILE_PATH = getFilePath();

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructs a Duke instance.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Getter for ui.
     * @return The ui object.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Gets user Response
     * @param input User input.
     * @return The response from Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                System.exit(0);
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }

    private static String getFilePath() {
        if (System.getProperty("user.dir").endsWith("text-ui-test")) {
            return System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length() - 13)
                    + "/data/duke.txt";
        } else {
            return System.getProperty("user.dir") + "/data/duke.txt";
        }
    }
}
