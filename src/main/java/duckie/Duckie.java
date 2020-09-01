package duckie;

import java.nio.file.Path;
import java.nio.file.Paths;

import duckie.command.Command;
import duckie.exception.DuckieException;
import duckie.task.TaskList;
import duckie.ui.Ui;
import javafx.application.Platform;


/**
 * Main file for the chatbot Duckie
 */
public class Duckie {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String cwd = System.getProperty("user.dir");
    private Path filePath = Paths.get(cwd, "data", "duckie.txt");

    /**
     * Instantiate the Duckie object
     */
    public Duckie() {
        ui = new Ui();
        ui.showIntro();
        storage = new Storage(String.valueOf(filePath));
        try {
            tasks = new TaskList(storage.load());
        } catch (DuckieException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Activates the chatbot to take in commands
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DuckieException e) {
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
            Command c = Parser.parse(input.trim());
            if (c.isExit()) {
                Platform.exit();
            }
            return c.execute(tasks, ui, storage);
        } catch (DuckieException e) {
            return e.getMessage();
        }
    }

}
