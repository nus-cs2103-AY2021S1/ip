package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke { //extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Duke bot.
     */
    public Duke() {
        this.storage = new Storage("tasks.txt");
        this.ui = new Ui();
        try {
            tasks = new TaskList(Storage.readFile());
        } catch (IOException | DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Constructs Duke bot.
     *
     * @param filePath File path containing data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            tasks = new TaskList(Storage.readFile());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Runs the program.
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    /**
     * Runs the Duke bot.
     */
    public void run() {
        ui.greet();
        //storage.showData();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                //c.executeGUI(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
