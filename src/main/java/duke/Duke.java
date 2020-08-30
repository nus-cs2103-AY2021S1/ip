package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke object with filePath of data\tasks.csv
     */
    public Duke() {
        this.storage = new Storage("data/tasks.csv");
        this.tasks = new TaskList(this.storage.read());
        this.ui = new Ui();
    }

    /**
     * Constructs a Duke object with the filePath specified
     * @param filePath specifies the directory of the csv file to read from/write to
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.read());
        this.ui = new Ui();
    }

    /**
     * Runs Duke
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        new Duke("data/tasks.csv").run();
    }
}
