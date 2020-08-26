package duke;

import commands.Command;

import exceptions.DukeException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class that initiates the Duke object. Contains methods to run the duke bot.
 * the appropriate Command object.
 */
public class Duke {

    // path of the data to be stored
    private static final Path path = Paths.get(".", "data", "duke.txt");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage(path);
            tasks = new TaskList(storage.getCurrentTasks());
        } catch (DukeException e) {
            ui.errorMessage(e.getMessage());
        }
    }

    /**
     * Method to run the duke bot. Controls the interaction between different classes to
     * achieve the bot behavior. Method stops when "bye" command is entered.
     *
     */
    public void runDuke() {
        ui.welcomeMessage(tasks.toString());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.executeCommand(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.errorMessage(e.getMessage());
            }
        }
        storage.saveFile(tasks.getTaskList());
    }


    public static void main(String[] args) {
        new Duke().runDuke();
    }
}
