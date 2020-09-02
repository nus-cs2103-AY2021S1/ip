package dude;

import java.io.FileNotFoundException;

import dude.command.Command;
import dude.util.CommandException;
import dude.util.CorruptedFileException;
import dude.util.InvalidArgumentException;
import dude.util.InvalidCommandException;
import dude.util.Storage;
import dude.util.TaskList;
import dude.ui.Ui;

/**
 * The main class containing the key functionality of the bot.
 */
public class Dude {
    private static final String FILEPATH = "./data/tasks.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructor for Dude class.
     */
    public Dude() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.read());
        } catch (FileNotFoundException e) {
            ui.showFileNotFound();
            tasks = new TaskList();
        } catch (CorruptedFileException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts Dude bot by accepting user input.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui , storage);
                isExit = command.getExitStatus();
                ui.showMessage();
            } catch (CommandException | InvalidArgumentException | InvalidCommandException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
